package com.orange.teleservice.controller;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.orange.teleservice.dto.SlotRequestDto;
import com.orange.teleservice.dto.SlotResponseDto;
import com.orange.teleservice.service.SlotsService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class SlotsControllerTest {

	@InjectMocks
	SlotsController slotsController;

	@Mock
	SlotsService slotsService;

	@Test
	public void testGetAllAvailableSlots() {
		
		List<SlotResponseDto> response=new ArrayList<>();
		Mockito.when(slotsService.getAllAvailableSlotsByUserIdAndEventIdAndRole(1L, 1L, "ADMIN")).thenReturn(response);
		ResponseEntity<List<SlotResponseDto>> allAvailableSlots = slotsController.getAllAvailableSlots(1L, 1L, "ADMIN");
		assertEquals(200, allAvailableSlots.getStatusCode().value());
		
	}
	@Test
	public void testCreateSlotsForAdmin() throws NoSuchAlgorithmException {
		SlotRequestDto slotRequestDto=new SlotRequestDto();
		SlotResponseDto slotResponseDto=new SlotResponseDto();
		Mockito.when(slotsService.createSlotsForAdmin(slotRequestDto)).thenReturn(slotResponseDto);
		
		ResponseEntity<SlotResponseDto> allAvailableSlots = slotsController.createSlotsForAdmin(slotRequestDto);
		assertEquals(200, allAvailableSlots.getStatusCode().value());
		
	}
	@Test
	public void testCreateSlotsForSales() {
		
		SlotRequestDto slotRequestDto = new SlotRequestDto();
		SlotResponseDto slotResponseDto =new SlotResponseDto();
		Mockito.when(slotsService.createSlotsForSales(slotRequestDto)).thenReturn(slotResponseDto);
		
		ResponseEntity<SlotResponseDto> allAvailableSlots = slotsController.createSlotsForSales(slotRequestDto);
		assertEquals(200, allAvailableSlots.getStatusCode().value());
		
	}
}
