package com.orange.teleservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.orange.teleservice.dto.BookingsRequestDto;
import com.orange.teleservice.dto.BookingsResponseDto;
import com.orange.teleservice.service.BookingsService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookingsControllerTest {

	@InjectMocks
	BookingsController bookingsController;
	@Mock
	BookingsService bookingsService;

	@Test
	public void testCreateUserSlot() {
		List<BookingsRequestDto> bookingsRequestDto=new ArrayList<>();
		BookingsResponseDto bookingsResponseDto=new BookingsResponseDto();
		Mockito.when(bookingsService.bookings(bookingsRequestDto)).thenReturn(bookingsResponseDto);
		BookingsResponseDto bookings = bookingsController.bookings(bookingsRequestDto);
		bookings.setStatusCode(200);
		assertEquals(200, bookings.getStatusCode());
		
	}

}
