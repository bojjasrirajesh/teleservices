package com.orange.teleservice.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdEvents {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long eventId;
	private Long planId;
	private String evenName;
	private String eventDate;
	private LocalTime startTime;
	private LocalTime endTime;
}
