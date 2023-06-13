package com.earl.email2.accountfactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StringFacilityTest {

	@Test
	void testEmailWord() {
		assertEquals(StringFacility.emailWord(""), "");
		assertEquals(StringFacility.emailWord("Blanchy Babe"), "Blanchy_Babe");
		assertEquals(StringFacility.emailWord("Harris, Jr."), "Harris_Jr");
		assertEquals(StringFacility.emailWord("Foo "), "Foo");
		assertEquals(StringFacility.emailWord("Foo  Bar"), "Foo_Bar");
	}

}
