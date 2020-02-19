package com.orange.teleservice.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.SlotRequestDto;
import com.orange.teleservice.dto.SlotResponseDto;
import com.orange.teleservice.entity.AdEvents;
import com.orange.teleservice.entity.Plans;
import com.orange.teleservice.entity.Slots;
import com.orange.teleservice.exception.DateRangeException;
import com.orange.teleservice.exception.SlotDateRangeException;
import com.orange.teleservice.repository.AdEventsRepository;
import com.orange.teleservice.repository.BookingsRepository;
import com.orange.teleservice.repository.PlanRepository;
import com.orange.teleservice.repository.SlotRepository;
import com.orange.teleservice.util.DateUtils;

@Service
public class SlotsServiceImpl implements SlotsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SlotsServiceImpl.class);

	@Autowired
	SlotRepository slotRepository;
	
	@Autowired
	PlanRepository planRepository;
	
	@Autowired
	AdEventsRepository adEventRepository;
	
	@Autowired
	BookingsRepository bookingsRepository;

	/**
	 * Method to get all available slots for admin and sales users.
	 */
	@Override
	public List<SlotResponseDto> getAllAvailableSlotsByUserIdAndEventIdAndRole(Long userId, Long eventId, String role) {
		LOGGER.info("SlotsServiceImpl :: getAllAvailableSlotsByUserIdAndEventIdAndRole.");
		List<SlotResponseDto> slotResponseDtoList = new ArrayList<>();
		if (role.equalsIgnoreCase(TeleServiceConstant.SALES)) {
			List<Slots> adminSlotList = slotRepository.findByEventIdAndRoleName(eventId, TeleServiceConstant.ADMIN);
			for (Slots slot : adminSlotList) {
				List<Slots> saleSlotsList = slotRepository.findByRefIdAndUserId(slot.getRefId(), userId);// SalesUserId
				SlotResponseDto slotResponseDto = new SlotResponseDto();
				BeanUtils.copyProperties(slot, slotResponseDto);
				slotResponseDto.setSubSlots(saleSlotsList);
				slotResponseDtoList.add(slotResponseDto);
			}
		} else if (role.equalsIgnoreCase(TeleServiceConstant.ADMIN)) {
			List<Slots> allSlotList = slotRepository.findByUserIdAndEventId(userId, eventId);
			for (Slots slot : allSlotList) {
				SlotResponseDto slotResponseDto = new SlotResponseDto();
				BeanUtils.copyProperties(slot, slotResponseDto);
				slotResponseDtoList.add(slotResponseDto);
			}
		}
		return slotResponseDtoList;
	}

	/**
	 * Method to create slots for admin users.
	 * @throws NoSuchAlgorithmException 
	 */
	@Override
	public SlotResponseDto createSlotsForAdmin(SlotRequestDto slotRequestDto) throws NoSuchAlgorithmException {
		LOGGER.info("Inside SlotsServiceImpl :: createSlotsForAdmin.");
		Random random = SecureRandom.getInstanceStrong();
		for (Slots slot : slotRequestDto.getSubSlots()) {			
			slot.setStartTime(slot.getStartTime());
			slot.setEndTime(slot.getEndTime());			
			slot.setRefId(random.nextInt(100));
			slot.setSlotDate(DateUtils.getCurrentDate());
			slot.setStatus(TeleServiceConstant.AVAILABLE);
			slot.setUserId(slotRequestDto.getUserId());
			slot.setEventId(slotRequestDto.getEventId());
			slot.setRoleName(slotRequestDto.getRoleName());
			slotRepository.save(slot);
		}		
		//send back response.
		SlotResponseDto slotResponseDto = new SlotResponseDto();
		slotResponseDto.setMessage(TeleServiceConstant.SUCCESS);
		slotResponseDto.setStatusCode(HttpStatus.OK.value());
		return slotResponseDto;
	}

	/**
	 * Method to create slots for sales manager.
	 */
	@Override
	public SlotResponseDto createSlotsForSales(SlotRequestDto slotRequestDto) {
		LOGGER.info("Inside SlotsServiceImpl :: createSlotsForSales");
		List<Slots> dbSlotList = new ArrayList<>();
		SlotResponseDto slotResponseDto = new SlotResponseDto();
		List<Slots> uiSlots = slotRequestDto.getSubSlots();
		LocalTime adminStartTime = slotRequestDto.getStartTime();
		LocalTime adminEndTime = slotRequestDto.getEndTime();
		if(!uiSlots.isEmpty()) {
			for (Slots slot : uiSlots) {
				
				//date validation for parent/admin time slot.
				if(!(slot.getStartTime().isAfter(adminStartTime) && slot.getEndTime().isBefore(adminEndTime))) {
					throw new DateRangeException("Please select the time range between : Start Time - "+adminStartTime+ " End Time - "+adminEndTime);
				}
								
				//************************************date validation for sub-slot or overlap slots*****************************************************//
				List<Slots> slotsForValidation = slotRepository.findByUserIdAndRefIdAndStartTimeLessThanAndEndTimeGreaterThan(
						slotRequestDto.getUserId(), 
						slotRequestDto.getRefId(),
						slot.getStartTime(),
						slot.getStartTime()
						);
				
				if (slotsForValidation.size() > 0) {
					throw new SlotDateRangeException("Slot already created please select different slots : Start Time - " +slot.getStartTime()+" : End Time - "+slot.getEndTime());
				}
				//************************************************************************************************************************************//
				
				slot.setRefId(slotRequestDto.getRefId());
				slot.setEventId(slotRequestDto.getEventId());
				slot.setStatus(TeleServiceConstant.AVAILABLE);
				slot.setSlotDate(DateUtils.getCurrentDate());
				slot.setRoleName(TeleServiceConstant.SALES);
				slot.setUserId(slotRequestDto.getUserId());
				int duration = slot.getEndTime().toSecondOfDay() - slot.getStartTime().toSecondOfDay();
				Optional<AdEvents> event = adEventRepository.findById(slot.getEventId());
				if (event.isPresent()) {
					Optional<Plans> plan = planRepository.findById(event.get().getPlanId());
					if(plan.isPresent()) {
						Double charges = plan.get().getCharges();
						slot.setTotalCharges(charges * duration);
						slot.setTotalDuration(duration);
					}									
				}
				dbSlotList.add(slot);
			}
		}		
		slotRepository.saveAll(dbSlotList);
		slotResponseDto.setMessage(TeleServiceConstant.SUCCESS);
		slotResponseDto.setStatusCode(HttpStatus.OK.value());
		return slotResponseDto;
	}

	/**
	 * Method to get all available slots based on sales manager user.
	 */
	@Override
	public List<SlotResponseDto> getAllAvailableSalesSlots(Long userId) {
		LOGGER.info("Inside SlotsServiceImpl :: getAllAvailableSalesSlots");
		Multimap<Long, Slots> slotsMaps = HashMultimap.create();
		
		List<Slots> salesAvailableSlotsList = slotRepository.findByUserId(userId);
		for(Slots salesSlot : salesAvailableSlotsList) {
			slotsMaps.put(salesSlot.getEventId(), salesSlot);
		}
		Map<Long, Collection<Slots>> slotMapTemp = slotsMaps.asMap();
		List<SlotResponseDto> responseDtosList = new ArrayList<>();
		for (Map.Entry<Long, Collection<Slots>> entry : slotMapTemp.entrySet()) {
			SlotResponseDto dto = new SlotResponseDto();
		
			//get event name
			Optional<AdEvents> event = adEventRepository.findById(entry.getKey());
			if(event.isPresent()) {
				dto.setEvenName(event.get().getEvenName());
			}			
			dto.setSubSlots(entry.getValue().stream().collect(Collectors.toList()));	
			
			//add to main list.
			responseDtosList.add(dto);
		} 		
		return responseDtosList;
	}
}
