package com.orange.teleservice.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.orange.teleservice.dto.EventResponseDto;
import com.orange.teleservice.entity.AdEvents;
import com.orange.teleservice.service.AdEventServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class AdEventControllerTest {

	@InjectMocks
	AdEventController eventController;
	@Mock
	AdEventServiceImpl eventServiceImpl;

	@Test
	public void getAllEvent() {
		EventResponseDto eventResponseDto = new EventResponseDto();
		AdEvents events = new AdEvents();
		events.setEndTime(LocalTime.now());
		events.setEvenName("India Pakistan Match");
		events.setEventDate(LocalDate.now().toString());
		events.setEventId(1L);
		events.setPlanId(1L);
		events.setStartTime(LocalTime.now());
		List<AdEvents> eventsList = new ArrayList<AdEvents>();
		eventsList.add(events);
		eventResponseDto.setEvents(eventsList);
		Mockito.when(eventServiceImpl.getAllEvent()).thenReturn(eventResponseDto);
		ResponseEntity<EventResponseDto> eventResponse = eventController.getAllEvent();
		Assert.assertNotNull(eventResponse);
		Assert.assertEquals(HttpStatus.OK, eventResponse.getStatusCode());
	}

}
