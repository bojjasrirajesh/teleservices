package com.orange.teleservice.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.teleservice.dto.SlotRequestDto;
import com.orange.teleservice.dto.SlotResponseDto;
import com.orange.teleservice.service.SlotsService;

@RestController
@RequestMapping("/slots")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class SlotsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SlotsController.class);

	@Autowired
	SlotsService slotsService;

	/**
	 * API to get all available slot information. 
	 * @return send success code.
	 */
	@GetMapping("/{userId}/{eventId}/{role}")
	public ResponseEntity<List<SlotResponseDto>> getAllAvailableSlots(@PathVariable("userId") Long userId,
			@PathVariable("eventId") Long eventId, @PathVariable("role") String role) {
		LOGGER.info("Inside SlotsController :: getAllAvailableSlots.");
		List<SlotResponseDto> slotResponseDto = slotsService.getAllAvailableSlotsByUserIdAndEventIdAndRole(userId,
				eventId, role);
		return new ResponseEntity<>(slotResponseDto, HttpStatus.OK);
	}

	/**
	 * API to create slots for admin.
	 * 
	 * @param slotRequestDto
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	@PostMapping("/admin")
	public ResponseEntity<SlotResponseDto> createSlotsForAdmin(@RequestBody SlotRequestDto slotRequestDto) throws NoSuchAlgorithmException {
		LOGGER.info("Inside SlotsController :: createSlotsForAdmin.");
		SlotResponseDto slotResponseDto = slotsService.createSlotsForAdmin(slotRequestDto);
		return new ResponseEntity<>(slotResponseDto, HttpStatus.OK);
	}

	/**
	 * API to create slots for sales. 
	 * 
	 * @param slotRequestDto
	 * @return
	 */
	@PostMapping("/sales")
	public ResponseEntity<SlotResponseDto> createSlotsForSales(@RequestBody SlotRequestDto slotRequestDto) {
		LOGGER.info("Inside SlotsController :: createSlotsForSales.");
		SlotResponseDto slotResponseDto = slotsService.createSlotsForSales(slotRequestDto);
		return new ResponseEntity<>(slotResponseDto, HttpStatus.OK);
	}
	
	/**
	 * API to get all available slot information for sales manager. 
	 * @return send success code.
	 */
	@GetMapping("/{userId}")
	public ResponseEntity<List<SlotResponseDto>> getAllAvailableSalesSlots(@PathVariable("userId") Long userId) {
		LOGGER.info("Inside SlotsController :: getAllAvailableSalesSlots.");
		List<SlotResponseDto> salesSlotsList = slotsService.getAllAvailableSalesSlots(userId);
		return new ResponseEntity<>(salesSlotsList, HttpStatus.OK);
	}
}
