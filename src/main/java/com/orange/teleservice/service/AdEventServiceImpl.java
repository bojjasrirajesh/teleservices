package com.orange.teleservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.EventResponseDto;
import com.orange.teleservice.entity.AdEvents;
import com.orange.teleservice.exception.AdEventNotFoundException;
import com.orange.teleservice.repository.AdEventsRepository;

/**
 * @author Shankar
 *
 *         This class is used to get all Events
 * 
 */

@Service
public class AdEventServiceImpl implements AdEventService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdEventServiceImpl.class);

	@Autowired
	AdEventsRepository eventRepository;

	/**
	 *  Method is used to get all the events.  
	 * @param
	 * @return EventResponseDto
	 */

	@Override
	public EventResponseDto getAllEvent() {
		LOGGER.info("Inside AdEventServiceImpl :: getAllEvent");
		EventResponseDto eventResponseDto = new EventResponseDto();
		List<AdEvents> eventsList = eventRepository.findAll();		
		if (eventsList.isEmpty()) {
			throw new AdEventNotFoundException(TeleServiceConstant.EVENT_NOT_FOUND);
		} else {
			eventResponseDto.setEvents(eventsList);
		}
		return eventResponseDto;
	}
}
