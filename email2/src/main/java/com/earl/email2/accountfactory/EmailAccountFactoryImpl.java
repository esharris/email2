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
		this.companySuffix = StringFacility.emailWord(companySuffix);
		this.defaultMailboxCapacity = defaultMailboxCapacity;
	}

	@Override
	public EmailAccount create(String firstName, String lastName, String department) {
		String firstName1 = StringFacility.emailWord(firstName);
		String lastName1 = StringFacility.emailWord(lastName);
		String department1 = StringFacility.emailWord(department);
		String password = randomPasswordGenerator.nextPassword(defaultPasswordLength);
		long uniqueCode = emailAccountDisambiguator.generateUniqueCode(firstName1, lastName1, department1);
		String email = firstName1.toLowerCase() + "." + lastName1.toLowerCase()
				+ (uniqueCode == 1 ? "" : "." + uniqueCode) + "@" + department1 + (department1.equals("") ? "" : ".")
				+ companySuffix;
		return new EmailAccount(firstName, lastName, department1, password, email, defaultMailboxCapacity, "");
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
