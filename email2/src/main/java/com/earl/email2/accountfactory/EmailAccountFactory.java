package com.earl.email2.accountfactory;

import com.earl.email2.account.EmailAccount;

public interface EmailAccountFactory {

	EmailAccount create(String firstName, String lastName, String department);

}