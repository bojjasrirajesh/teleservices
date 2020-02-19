package com.orange.teleservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.teleservice.dto.EventResponseDto;
import com.orange.teleservice.service.AdEventService;

/**
 * 
 * @author Shankar
 * @version 1.0
 * @since 17-02-2020
 */

@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class AdEventController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdEventController.class);

	@Autowired
	AdEventService eventService;

	/**
	 * This method is used to get all event data 
	 * @param
	 * @return EventResponseDto
	 */
	@GetMapping("/events")
	public ResponseEntity<EventResponseDto> getAllEvent() {
		LOGGER.info("Inside AdEventController :: getAllEvents.");
		return new ResponseEntity<>(eventService.getAllEvent(), HttpStatus.OK);
	}
}
