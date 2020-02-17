package com.orange.teleservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Events {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eventId;
	private Long planId;
	private String evenName;
	private String eventDate;
	private String startTime;
	private String endTime;
}
