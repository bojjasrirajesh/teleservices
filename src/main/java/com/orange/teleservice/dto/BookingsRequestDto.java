package com.orange.teleservice.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingsRequestDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long eventId;
	private Long slotId;
	private Long userId;
	private Integer refId;	
	private String adAgencyName;
}
