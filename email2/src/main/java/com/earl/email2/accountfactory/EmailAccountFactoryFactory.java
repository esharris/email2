package com.earl.email2.accountfactory;

public interface EmailAccountFactoryFactory {

	EmailAccountFactory create(int defaultPasswordLength, String companySuffix, int defaultMailboxCapacity);

}