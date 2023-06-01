package com.earl.email2;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class EmailAccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -52766338684377008L;

	public EmailAccountNotFoundException(String message) {
		super(message);
	}
}
