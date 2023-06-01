package com.earl.email2.accountfactory;

import com.earl.email2.account.EmailAccount;
import com.earl.email2.random.RandomPasswordGenerator;

public class EmailAccountFactoryImpl implements EmailAccountFactory {

	private final RandomPasswordGenerator randomPasswordGenerator;
	private final EmailAccountDisambiguator emailAccountDisambiguator;
	private final int defaultPasswordLength;
	private final String companySuffix;
	private final int defaultMailboxCapacity;

	public EmailAccountFactoryImpl(RandomPasswordGenerator randomPasswordGenerator,
			EmailAccountDisambiguator emailAccouuntDisambiguator, int defaultPasswordLength, String companySuffix,
			int defaultMailboxCapacity) {
		super();
		this.randomPasswordGenerator = randomPasswordGenerator;
		this.emailAccountDisambiguator = emailAccouuntDisambiguator;
		this.defaultPasswordLength = defaultPasswordLength;
		this.companySuffix = companySuffix;
		this.defaultMailboxCapacity = defaultMailboxCapacity;
	}

	@Override
	public EmailAccount create(String firstName, String lastName, String department) {
		String password = randomPasswordGenerator.nextPassword(defaultPasswordLength);
		long uniqueCode = emailAccountDisambiguator.generateUniqueCode(firstName, lastName, department);
		String email = firstName.toLowerCase() + "." + lastName.toLowerCase()
				+ (uniqueCode == 1 ? "" : "." + uniqueCode) + "@" + department + (department.equals("") ? "" : ".")
				+ companySuffix;
		return new EmailAccount(firstName, lastName, department, password, email, defaultMailboxCapacity, "");
	}

	public RandomPasswordGenerator getRandomPasswordGenerator() {
		return randomPasswordGenerator;
	}

	public EmailAccountDisambiguator getEmailAccountDisambiguator() {
		return emailAccountDisambiguator;
	}

	public int getDefaultPasswordLength() {
		return defaultPasswordLength;
	}

	public String getCompanySuffix() {
		return companySuffix;
	}

	public int getDefaultMailboxCapacity() {
		return defaultMailboxCapacity;
	}

}
