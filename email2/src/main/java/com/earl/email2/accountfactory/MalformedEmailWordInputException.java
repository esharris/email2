package com.earl.email2.accountfactory;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class MalformedEmailWordInputException extends RuntimeException {

	private static final long serialVersionUID = -8029224439690404543L;

	public MalformedEmailWordInputException(String emailWordInput) {
		super("Malformed email word input: \"" + emailWordInput + "\"");

	}

}
