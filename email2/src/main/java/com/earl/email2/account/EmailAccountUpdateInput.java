package com.earl.email2.account;

public record EmailAccountUpdateInput( //
		String firstName, //
		String lastName, //
		String department, //
		String password, //
		Integer mailboxCapacity, //
		String alternateEmail) {
}
