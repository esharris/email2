package com.earl.email2.account;

public record EmailAccountUpdateInput( //
		String firstName, //
		String lastName, //
		String department, //
		String password, //
		int mailboxCapacity, //
		String alternateEmail) {
}
