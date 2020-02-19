package com.orange.teleservice.dto;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class BookingsResponseDto {
	private String message;
	private Integer statusCode;
	private Long bookingsId;
}
