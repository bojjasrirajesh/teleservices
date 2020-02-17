package com.orange.teleservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.orange.teleservice.dto.ResponseDto;
import com.orange.teleservice.util.TeleServiceUtil;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ResponseDto> NoUserDataAvaliableException() {
		ResponseDto responseDto = new ResponseDto();
		responseDto.setMessage(TeleServiceUtil.INVALID_LOGIN);
		responseDto.setStatusCode(TeleServiceUtil.NOTFOUND_CODE);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
	}
}