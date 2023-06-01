package com.earl.email2.random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RandomPasswordGeneratorImpl implements RandomPasswordGenerator {

	private static final String LEGAL_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ012345667789!@#$%";

	private final RandomCharGenerator randomCharGenerator;

	@Autowired
	public RandomPasswordGeneratorImpl(RandomCharGenerator randomCharGenerator) {
		this.randomCharGenerator = randomCharGenerator;
	}

	@Override
	public String nextPassword(int length) {
		char[] password = new char[length];
		for (int i = 0; i < length; i++) {
			password[i] = randomCharGenerator.nextChar(LEGAL_CHARS);
		}
		return new String(password);
	}

	public RandomCharGenerator getRandomCharGenerator() {
		return randomCharGenerator;
	}
}
