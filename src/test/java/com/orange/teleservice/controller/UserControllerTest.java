package com.orange.teleservice.controller;

import java.util.ArrayList;



import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.orange.teleservice.dto.LoginDto;
import com.orange.teleservice.dto.LoginResponseDto;
import com.orange.teleservice.entity.Users;
import com.orange.teleservice.service.UserServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {
	
	  @InjectMocks 
	  UserController userController;
	  
	  @Mock 
	  UserServiceImpl userServiceImpl;
	  
	  @Test 
	  public void testusersLoginPossitive() {
	  
	  List<Users> listUsers=new ArrayList<>();
	  Users user=new Users();
	  user.setUserId(new
	  Long(1));user.setMobile("289734");user.setEmail("a@b.com");
	  user.setUserName("rajesh");
	  user.setPassword("8283jks");
	  listUsers.add(user);
	  LoginDto loginDto=new LoginDto(); 
	  loginDto.setMobile(user.getMobile());
	  loginDto.setPassword(user.getPassword());
	  LoginResponseDto loginResponseDto=new LoginResponseDto();
	  loginResponseDto.setMessage("Login Success");
	  loginResponseDto.setStatusCode(200); loginResponseDto.setUserId(new Long(1));
	  Mockito.when(userServiceImpl.usersLogin(loginDto)).thenReturn(loginResponseDto);
	  ResponseEntity<LoginResponseDto> loginRespons = userController.usersLogin(loginDto);
	  Assert.assertNotNull( loginRespons);
	  Assert.assertEquals(HttpStatus.OK,loginRespons.getStatusCode()); }	
	
}
