package com.orange.teleservice.dto;

import java.util.List;

import com.orange.teleservice.entity.AdEvents;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResponseDto {
	private List<AdEvents> events;
}
