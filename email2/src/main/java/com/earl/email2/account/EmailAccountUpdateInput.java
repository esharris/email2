package com.earl.email2.account;

import jakarta.validation.constraints.Email;

public record EmailAccountUpdateInput( //
		String firstName, //
		String lastName, //
		String department, //
		String password, //
		Integer mailboxCapacity, //
		@Email(message = "alternate email must be well-formed") String alternateEmail) {
}
