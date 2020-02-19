package com.orange.teleservice.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orange.teleservice.constant.TeleServiceConstant;
import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.entity.Users;
import com.orange.teleservice.exception.UserNotFoundException;
import com.orange.teleservice.repository.UserRepository;

/**
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
		LOGGER.info("Inside BookingsServiceImpl :: usersLogin.");
		Users users = userRepository.findByMobileAndPassword(loginDto.getMobile(), loginDto.getPassword());
		LoginResponseDto responseDto = new LoginResponseDto();
		if (!Objects.isNull(users)) {			
			responseDto.setUserId(users.getUserId());
			responseDto.setRoleName(users.getRoleName());
			responseDto.setMessage(TeleServiceConstant.SUCCESS);
		} else {
			responseDto.setMessage(TeleServiceConstant.INVALID_USER);
			throw new UserNotFoundException(TeleServiceConstant.INVALID_LOGIN);
		}
		return responseDto;
	}
}
