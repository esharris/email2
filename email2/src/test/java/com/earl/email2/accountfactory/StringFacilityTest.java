package com.earl.email2.accountfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class StringFacilityTest {

	@Test
	void testEmailWord() {
		/**
		 * Result can't be empty
		 */
		assertThrows(MalformedEmailWordInputException.class, () -> StringFacility.emailWord(""));
		assertEquals("Blanchy_Babe", StringFacility.emailWord("Blanchy Babe"));
		assertEquals("Harris_Jr", StringFacility.emailWord("Harris, Jr."));
		assertEquals("Foo", StringFacility.emailWord("Foo "));
		assertEquals("Foo_Bar", StringFacility.emailWord("Foo  Bar"));
		assertEquals("boo", StringFacility.emailWord("boo__"));

		/**
		 * Must start with alphabetic
		 */
		assertThrows(MalformedEmailWordInputException.class, () -> StringFacility.emailWord("2Short"));
		assertThrows(MalformedEmailWordInputException.class, () -> StringFacility.emailWord("$Short"));
		assertThrows(MalformedEmailWordInputException.class, () -> StringFacility.emailWord(" Foo"));

		/**
		 * Result can't be empty
		 */
		assertThrows(MalformedEmailWordInputException.class, () -> StringFacility.emailWord("___"));
	}

}
