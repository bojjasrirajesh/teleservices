package com.orange.teleservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.entity.Users;
import com.orange.teleservice.exception.UserNotFoundException;
import com.orange.teleservice.repository.UserRepository;


@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceImplTest {

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Mock
	UserRepository userRepository;

	@Test
	public void testUsersLoginPossitive() {
		Users user = new Users();
		user.setUserId(new Long(1));
		user.setMobile("289734");
		user.setEmail("a@b.com");
		user.setUserName("rajesh");
		user.setPassword("8283jks");
		LoginDto loginDto = new LoginDto();
		loginDto.setMobile(user.getUserName());
		loginDto.setPassword(user.getPassword());
		Mockito.when(userRepository.findByMobileAndPassword(loginDto.getMobile(), loginDto.getPassword())).thenReturn(user);
		LoginResponseDto loginResponseDto=new LoginResponseDto();
		
		 loginResponseDto=userServiceImpl.usersLogin(loginDto);
		 loginResponseDto.setStatusCode(200);
		 assertEquals(200,loginResponseDto.getStatusCode());
	}

	@Test(expected = UserNotFoundException.class)
	public void testUsersLoginNegative() {
		Users user = new Users();
		LoginDto loginDto = new LoginDto();
		Mockito.when(userRepository.findByMobileAndPassword("", "")).thenReturn(user);
		userServiceImpl.usersLogin(loginDto);
	}

}
