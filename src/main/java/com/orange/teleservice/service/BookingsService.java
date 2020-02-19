package com.orange.teleservice.service;

import java.util.List;

import com.orange.teleservice.dto.BookingsRequestDto;
import com.orange.teleservice.dto.BookingsResponseDto;

public interface BookingsService {
	BookingsResponseDto bookings(List<BookingsRequestDto> bookingsRequestDto);
}
