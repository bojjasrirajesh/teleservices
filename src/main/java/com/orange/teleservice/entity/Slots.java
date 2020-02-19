package com.orange.teleservice.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Slots {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long slotId;
	private Long eventId;
	private Long userId;
	private String slotDate;
	private LocalTime startTime;
	private LocalTime endTime;
	private String status;
	private Integer refId;
	private String roleName;
	private Double totalCharges;
	private Integer totalDuration;
	private String adAgencyName;
}
