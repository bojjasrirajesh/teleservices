package com.orange.teleservice.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.orange.teleservice.entity.Slots;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SlotResponseDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String message;
	private Integer statusCode;
	
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
	private String evenName;
	private transient List<Slots> subSlots;
	private transient Map<Slots, String> subSlotsMap;
}
