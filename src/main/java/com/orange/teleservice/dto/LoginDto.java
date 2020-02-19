package com.orange.teleservice.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private String mobile;
	private String password;
}