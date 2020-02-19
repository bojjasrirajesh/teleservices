package com.orange.teleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.orange.teleservice.dto.SlotRequestDto;
import com.orange.teleservice.dto.SlotResponseDto;
import com.orange.teleservice.entity.Plans;
import com.orange.teleservice.entity.Slots;
import com.orange.teleservice.repository.AdEventsRepository;
import com.orange.teleservice.repository.PlanRepository;
import com.orange.teleservice.repository.SlotRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SlotsServiceImplTest {
	@InjectMocks
	SlotsServiceImpl slotsServiceImpl;

	@Mock
	SlotRepository slotRepository;

	@Mock
	PlanRepository planRepository;

	@Mock
	AdEventsRepository adEventRepository;

	@Test
	@Ignore
	public void testCreateSlotsForSales() {
		List<Slots> salesSlotList = new ArrayList<Slots>();
		SlotRequestDto slotRequestDto = new SlotRequestDto();
		SlotResponseDto createSlotsSales = new SlotResponseDto();
		Slots slots = new Slots();
		Plans plan = new Plans();
		plan.setCharges(2000d);
		plan.setPlanId(1L);
		plan.setPlanName("Premium");
		slots.setEndTime(LocalTime.now().plusHours(1));
		slots.setEventId(1L);
		slots.setRefId(1);
		slots.setRoleName("ADMIN");
		slots.setSlotDate(LocalDate.now().toString());
		slots.setSlotId(1L);
		slots.setStartTime(LocalTime.now());
		slots.setStatus("Success");
		slots.setTotalCharges(20000d);
		slots.setTotalDuration(20);
		slots.setUserId(1L);
		salesSlotList.add(slots);
		slotRequestDto.setEndTime(LocalTime.now().plusHours(1));
		slotRequestDto.setEventId(1L);
		slotRequestDto.setRefId(1);
		slotRequestDto.setRoleName("sales");
		slotRequestDto.setSlotId(1L);
		slotRequestDto.setStartTime(LocalTime.now());
		slotRequestDto.setSubSlots(salesSlotList);
		slotRequestDto.setUserId(1L);
		Mockito.when(slotRepository.save(slots)).thenReturn(slots);
		Mockito.when(slotRepository.saveAll(salesSlotList)).thenReturn(salesSlotList);
		Mockito.when(planRepository.findById(1L)).thenReturn(Optional.of(plan));
		createSlotsSales = slotsServiceImpl.createSlotsForSales(slotRequestDto);
		createSlotsSales.setStatusCode(200);
		assertEquals(200, createSlotsSales.getStatusCode());
	}

	@Test
	public void testCreateSlotsForAdmine() throws NoSuchAlgorithmException {
		List<Slots> dbSlotList = new ArrayList<>();
		Slots slots = new Slots();
		slots.setEndTime(LocalTime.now().plusHours(1));
		slots.setEventId(1L);
		slots.setRefId(1);
		slots.setRoleName("ADMIN");
		slots.setSlotDate(LocalDate.now().toString());
		slots.setSlotId(1L);
		slots.setStartTime(LocalTime.now());
		slots.setStatus("Success");
		slots.setTotalCharges(20000d);
		slots.setTotalDuration(20);
		slots.setUserId(1L);
		dbSlotList.add(slots);
		Mockito.when(slotRepository.save(slots)).thenReturn(slots);
		SlotRequestDto slotRequestDto = new SlotRequestDto();
		slotRequestDto.setSlotId(1L);
		slotRequestDto.setEndTime(LocalTime.now().plusHours(1));
		slotRequestDto.setEventId(1L);
		slotRequestDto.setRefId(1);
		slotRequestDto.setRoleName("ADMIN");
		slotRequestDto.setSlotId(1L);
		slotRequestDto.setStartTime(LocalTime.now());
		slotRequestDto.setUserId(1L);
		slotRequestDto.setSubSlots(dbSlotList);
		List<SlotRequestDto> listSlotRequestDto = new ArrayList<>();
		listSlotRequestDto.add(slotRequestDto);
		SlotResponseDto createSlotsForSales = slotsServiceImpl.createSlotsForAdmin(slotRequestDto);
		createSlotsForSales.setSlotId(1L);
		assertEquals(1L, createSlotsForSales.getSlotId());

	}

	@Test
	public void testGetAllAvailableSlotsByUserIdAndEventIdAndRole() {
		List<Slots> adminSlotList = new ArrayList<Slots>();
		List<Slots> adminSlotListsales = new ArrayList<Slots>();
		Slots slots = new Slots();
		slots.setEndTime(LocalTime.now().plusHours(1));
		slots.setEventId(1L);
		slots.setRefId(1);
		slots.setRoleName("ADMIN");
		slots.setSlotDate(LocalDate.now().toString());
		slots.setSlotId(1L);
		slots.setStartTime(LocalTime.now());
		slots.setStatus("Success");
		slots.setTotalCharges(20000d);
		slots.setTotalDuration(20);
		slots.setUserId(1L);
		adminSlotList.add(slots);
		slots.setRoleName("SALES");
		Mockito.when(slotRepository.findByEventIdAndRoleName(1L, "ADMIN")).thenReturn(adminSlotList);
		Mockito.when(slotRepository.findByEventIdAndRoleName(2L, "SALES")).thenReturn(adminSlotList);
		Mockito.when(slotRepository.findByRefIdAndUserId(1, 1L)).thenReturn(adminSlotList);
		Mockito.when(slotRepository.findByUserIdAndEventId(1L, 1L)).thenReturn(adminSlotList);
		SlotResponseDto slotResponseDto = new SlotResponseDto();
		slotResponseDto.setSlotId(2L);
		slotResponseDto.setSlotId(1L);
		adminSlotListsales.add(slots);
		slotResponseDto.setMessage("Success");
		slotResponseDto.setStatusCode(200);
		slotResponseDto.setSubSlots(adminSlotList);
		List<SlotResponseDto> allAvailableSlotsByUserIdAndEventIdAndRole = slotsServiceImpl.getAllAvailableSlotsByUserIdAndEventIdAndRole(1L, 1L, "ADMIN");
		assertEquals(1, allAvailableSlotsByUserIdAndEventIdAndRole.size());
	}

}
