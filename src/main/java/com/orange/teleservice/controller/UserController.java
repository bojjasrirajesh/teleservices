package com.orange.teleservice.controller;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.service.UserService;
import com.orange.teleservice.util.TeleServiceUtil;

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
	public LoginResponseDto usersLogin(@RequestBody LoginDto loginDto) {
		LOGGER.info(TeleServiceUtil.LOGIN_METHOD);
		return userService.usersLogin(loginDto);
	}
}
