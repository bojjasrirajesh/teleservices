package com.orange.teleservice.dto;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.List;

import com.orange.teleservice.entity.Slots;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class SlotRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;	
	private Long eventId;
	private Long slotId;
	private Long userId;
	private Integer refId;
	private LocalTime startTime1;
	
	private LocalTime startTime;

	private LocalTime endTime;
	private String roleName;
	private transient List<Slots> subSlots;	
}
