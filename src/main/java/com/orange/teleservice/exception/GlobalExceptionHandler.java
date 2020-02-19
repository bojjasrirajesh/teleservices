package com.orange.teleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.ResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseDto> noUserDataAvaliableException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(TeleServiceConstant.INVALID_LOGIN);
		responseDto.setStatusCode(TeleServiceConstant.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}
	
	@ExceptionHandler(DateRangeException.class)
	public ResponseEntity<ResponseDto> dateRangeException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Please select the time range between start and end time of admin created slot.");
		responseDto.setStatusCode(TeleServiceConstant.BAD_REQUEST_CODE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}
	
	@ExceptionHandler(SlotDateRangeException.class)
	public ResponseEntity<ResponseDto> slotDateRangeException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage("Slot already created please select different slots.");
		responseDto.setStatusCode(TeleServiceConstant.BAD_REQUEST_CODE);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
	}
}