package com.orange.teleservice.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.entity.Users;
import com.orange.teleservice.repository.UserRepository;
import com.orange.teleservice.service.UserServiceImpl;

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
		loginDto.setUName(user.getUserName());
		loginDto.setPassword(user.getPassword());
		Mockito.when(userRepository.findByUserNameAndPassword(loginDto.getUName(), loginDto.getPassword()))
				.thenReturn(user);
		LoginResponseDto usersLogin = userServiceImpl.usersLogin(loginDto);
		Assert.assertEquals(usersLogin.getStatusCode(), new Integer(200));
	}
}
