package com.orange.teleservice.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.service.UserService;

/**
 * 
 * @author Shankar
 * @version 1.0
 * @since 17-02-2020
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;

	/**
	 * This method is used to validating the user by providing the input as LoginDto
	 * 
	 * @param loginDto
	 * @return responseDTO
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponseDto> usersLogin(@RequestBody LoginDto loginDto) {
		LOGGER.info("Inside UserController :: userLogin.");
		LoginResponseDto loginResponseDto = userService.usersLogin(loginDto);
		loginResponseDto.setMessage(TeleServiceConstant.LOGIN_SUCCESS);
		loginResponseDto.setStatusCode(HttpStatus.OK.value());
		return new ResponseEntity<>(loginResponseDto, HttpStatus.OK);
	}
}
