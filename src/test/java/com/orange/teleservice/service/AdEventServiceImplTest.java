package com.orange.teleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.orange.teleservice.dto.EventResponseDto;
import com.orange.teleservice.entity.AdEvents;
import com.orange.teleservice.exception.AdEventNotFoundException;
import com.orange.teleservice.repository.AdEventsRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AdEventServiceImplTest {

	@InjectMocks
	AdEventServiceImpl adEventServiceImpl;

	@Mock
	AdEventsRepository adEventsRepository;

	@Test
	public void getAllEventServiceTest() {
		AdEvents event = new AdEvents();
		event.setEventId(1L);
		event.setEvenName("India Pakistan Match");
		event.setPlanId(1L);
		event.setEventDate(LocalDate.now().plusDays(5).toString());
		event.setStartTime(LocalTime.now());
		event.setEndTime(LocalTime.now().plusMinutes(5));
		List<AdEvents> events = new ArrayList<AdEvents>();
		events.add(event);
		Mockito.when(adEventsRepository.findAll()).thenReturn(events);
		EventResponseDto eventResponseDto = adEventServiceImpl.getAllEvent();
		assertEquals(1, eventResponseDto.getEvents().size());
	}

	@Test(expected = AdEventNotFoundException.class)
	public void getAllEventServiceTestNegative() {
		List<AdEvents> events = new ArrayList<AdEvents>();
		Mockito.when(adEventsRepository.findAll()).thenReturn(events);
		EventResponseDto eventResponseDto = adEventServiceImpl.getAllEvent();
		assertEquals(0, eventResponseDto.getEvents().size());

	}

}
