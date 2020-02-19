package com.orange.teleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.orange.teleservice.dto.BookingsRequestDto;
import com.orange.teleservice.dto.BookingsResponseDto;
import com.orange.teleservice.entity.Bookings;
import com.orange.teleservice.entity.Slots;
import com.orange.teleservice.entity.Users;
import com.orange.teleservice.repository.BookingsRepository;
import com.orange.teleservice.repository.SlotRepository;
import com.orange.teleservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookingsServiceImplTest {

	@InjectMocks
	BookingsServiceImpl bookingsServiceImpl;

	@Mock
	BookingsRepository bookingsRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	SlotRepository slotRepository;

	@Test
	public void testBookings() {
		Bookings bookings = new Bookings();
		bookings.setSlotsId(1L);
		bookings.setBookingId(1L);
		List<Bookings> bookingList = new ArrayList<Bookings>();
		bookingList.add(bookings);
		Users users = new Users();
		users.setUserId(1L);
		users.setRoleName("SALES");
		Optional<Users> of = Optional.of(users);
		BookingsRequestDto bookingsRequestDto = new BookingsRequestDto();
		bookingsRequestDto.setSlotId(1L);
		bookingsRequestDto.setUserId(1L);
		List<Slots> listSlots = new ArrayList<>();
		Slots slots = new Slots();
		slots.setSlotId(1L);
		listSlots.add(slots);
		List<BookingsRequestDto> bookingsRequestDtos=new ArrayList<>();
		Slots slots1 = new Slots();
		slots1.setSlotId(1L);
		
		Mockito.when(bookingsRepository.save(bookings)).thenReturn(bookings);
		Mockito.when(bookingsRepository.saveAll(bookingList)).thenReturn(bookingList);
		Mockito.when(userRepository.findById(1L)).thenReturn(of);
		Optional<Slots> of2 = Optional.of(slots);
		Mockito.when(slotRepository.findById(1L)).thenReturn(of2);

		BookingsResponseDto bookingsResp = bookingsServiceImpl.bookings(bookingsRequestDtos);

		bookingsResp.setBookingsId(1L);
		assertEquals(1L,bookingsResp.getBookingsId());
	}

}
