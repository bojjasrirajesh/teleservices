package com.orange.teleservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.BookingsRequestDto;
import com.orange.teleservice.dto.BookingsResponseDto;
import com.orange.teleservice.service.BookingsService;

/**
 * This class is used to the book the slot by sales manager for the perticular
 * event created by admin
 * 
 * @author Rajesh
 * @version 1.0
 * @since 17-02-2020
 */

@RestController
@RequestMapping("/bookings")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class BookingsController {
	private static final Logger LOGGER = LoggerFactory.getLogger(BookingsController.class);
	@Autowired
	BookingsService bookingsService;

	/**
	 * This method is used to booking the slots for the adagency
	 * 
	 * @param bookingsRequestDto
	 * @return BookingsResponseDto
	 */
	@PostMapping
	public BookingsResponseDto bookings(@RequestBody List<BookingsRequestDto> bookingsRequestDto) {
		LOGGER.info("Inside BookingsController :: bookings");
		BookingsResponseDto bookingsResponseDto = bookingsService.bookings(bookingsRequestDto);
		bookingsResponseDto.setMessage(TeleServiceConstant.SLOT_BOOKED);
		bookingsResponseDto.setStatusCode(HttpStatus.OK.value());
		return bookingsResponseDto;
	}
}
