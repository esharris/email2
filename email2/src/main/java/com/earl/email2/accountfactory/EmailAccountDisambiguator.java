package com.earl.email2.accountfactory;

public interface EmailAccountDisambiguator {

	long generateUniqueCode(String firstName, String lastName, String department);

}