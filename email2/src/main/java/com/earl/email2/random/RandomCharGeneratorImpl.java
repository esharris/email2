package com.earl.email2.random;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomCharGeneratorImpl implements RandomCharGenerator {

	/**
	 * Java has other, better random number generators. But using inversion of
	 * control here is like trying to spit hairs.
	 */
	private final Random random = new Random();

	@Override
	public char nextChar(String legalChars) {
		int rand = random.nextInt(legalChars.length());
		return legalChars.charAt(rand);
	}

	public Random getRandom() {
		return random;
	}
}
