package com.earl.email2.accountfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.earl.email2.random.RandomPasswordGenerator;

@Component
public class EmailAccountFactoryFactoryImpl implements EmailAccountFactoryFactory {

	private final RandomPasswordGenerator randomPasswordGenerator;
	private final EmailAccountDisambiguator emailAccouuntDisambiguator;

	@Autowired
	public EmailAccountFactoryFactoryImpl(RandomPasswordGenerator randomPasswordGenerator,
			EmailAccountDisambiguator emailAccouuntDisambiguator) {
		super();
		this.randomPasswordGenerator = randomPasswordGenerator;
		this.emailAccouuntDisambiguator = emailAccouuntDisambiguator;
	}

	@Override
	public EmailAccountFactory create(int defaultPasswordLength, String companySuffix, int defaultMailboxCapacity) {
		return new EmailAccountFactoryImpl(randomPasswordGenerator, emailAccouuntDisambiguator, defaultPasswordLength,
				companySuffix, defaultMailboxCapacity);

	}

	public RandomPasswordGenerator getRandomPasswordGenerator() {
		return randomPasswordGenerator;
	}

	public EmailAccountDisambiguator getEmailAccouuntDisambiguator() {
		return emailAccouuntDisambiguator;
	}

}
