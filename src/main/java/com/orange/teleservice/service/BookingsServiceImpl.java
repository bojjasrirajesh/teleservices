package com.orange.teleservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.BookingsRequestDto;
import com.orange.teleservice.dto.BookingsResponseDto;
import com.orange.teleservice.entity.Bookings;
import com.orange.teleservice.entity.Slots;
import com.orange.teleservice.repository.BookingsRepository;
import com.orange.teleservice.repository.SlotRepository;
import com.orange.teleservice.util.DateUtils;

@Service
public class BookingsServiceImpl implements BookingsService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingsServiceImpl.class);
	
	@Autowired
	BookingsRepository bookingsRepository;

	@Autowired
	SlotRepository slotRepository;

	@Override
	public BookingsResponseDto bookings(List<BookingsRequestDto> bookingsRequestDto) {		
		LOGGER.info("Insode BookingsServiceImpl :: bookings.");
		BookingsResponseDto bookingsResponseDto = new BookingsResponseDto();
		List<Slots> listSlotsSaveList = new ArrayList<>();	
		List<Bookings> bookingsSaveList = new ArrayList<>();
		for(BookingsRequestDto dto : bookingsRequestDto) {
			Optional<Slots> dbSlot = slotRepository.findById(dto.getSlotId());
			
			//Booking details.
			Bookings bookings = new Bookings();
			
			//set slot status to booked.
			if(dbSlot.isPresent()) {
				dbSlot.get().setStatus(TeleServiceConstant.BOOKED);
				dbSlot.get().setAdAgencyName(dto.getAdAgencyName());
				listSlotsSaveList.add(dbSlot.get());
				bookings.setSlotsId(dbSlot.get().getSlotId());
			}		
			
			bookings.setBookingDate(DateUtils.getCurrentDate());
			bookings.setAdAgencyName(dto.getAdAgencyName());			
			bookings.setUserId(dto.getUserId());
			bookingsSaveList.add(bookings);
		}
		
		slotRepository.saveAll(listSlotsSaveList);
		bookingsRepository.saveAll(bookingsSaveList);
				
		bookingsResponseDto.setMessage(TeleServiceConstant.SLOT_BOOKED);
		bookingsResponseDto.setStatusCode(HttpStatus.OK.value());		
		return bookingsResponseDto;
	}
}
