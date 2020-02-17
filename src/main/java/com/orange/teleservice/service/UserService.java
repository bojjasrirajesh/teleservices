package com.orange.teleservice.service;

import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;

public interface UserService {

	LoginResponseDto usersLogin(LoginDto userDto);
}
