package com.earl.email2.accountfactory;

public class StringFacility {

	private static boolean isAlphanumeric(char c1) {
		return ('a' <= c1 && c1 <= 'z') || ('A' <= c1 && c1 <= 'Z') || ('0' <= c1 && c1 <= '9');
	}

	public static String emailWord(String string) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < string.length(); i++) {
			char c = string.charAt(i);
			if (c == '_' || c == '.') {
				// peek to make sure an alphanumeric character follows
				if (i < string.length() - 1) {
					char c1 = string.charAt(i + 1);
					if (isAlphanumeric(c1)) {
						stringBuilder.append('_');
					} else {
						// throw c away; an alphanumber must follow '_'
					}
				} else {
					// throw c away; last char can't be '_' or '.'
				}
			} else if (isAlphanumeric(c)) {
				stringBuilder.append(c);
			} else {
				// peek to make sure an alphanumeric character follows
				if (i < string.length() - 1) {
					char c1 = string.charAt(i + 1);
					if (isAlphanumeric(c1)) {
						stringBuilder.append('_');
					}
				} else {
					// throw c away
				}
			}
		}
		return stringBuilder.toString();
	}

}
