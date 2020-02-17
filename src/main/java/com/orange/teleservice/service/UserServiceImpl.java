package com.orange.teleservice.service;

import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.entity.Users;
import com.orange.teleservice.exception.UserNotFoundException;
import com.orange.teleservice.repository.UserRepository;
import com.orange.teleservice.util.TeleServiceUtil;

/*
 * @author Shankar
 *
 * method is used to check whether the user is valid or invalid
 * 
 */

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * @author Shankar
	 *
	 *         Method is used to check whether the user is valid or not
	 *
	 * @param UserDto.
	 * @return LoginResponseDto.
	 */

	@Override
	public LoginResponseDto usersLogin(LoginDto loginDto) {
		LOGGER.info(TeleServiceUtil.LOGIN_METHOD);
		Users users = userRepository.findByUserNameAndPassword(loginDto.getUName(), loginDto.getPassword());
		LoginResponseDto responseDto = new LoginResponseDto();
		if (!Objects.isNull(users)) {
			responseDto.setMessage(TeleServiceUtil.LOGIN_SUCCESS);
			responseDto.setStatusCode(HttpStatus.OK.value());
			responseDto.setUserId(users.getUserId());
		} else {
			throw new UserNotFoundException(TeleServiceUtil.INVALID_LOGIN);
		}
		return responseDto;
	}
}
