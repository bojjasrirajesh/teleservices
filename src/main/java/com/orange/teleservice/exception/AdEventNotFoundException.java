package com.orange.teleservice.exception;

public class AdEventNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public AdEventNotFoundException(String exception) {
		super(exception);
	}
}
