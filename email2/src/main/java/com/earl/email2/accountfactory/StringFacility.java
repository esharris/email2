package com.earl.email2.accountfactory;

public class StringFacility {

	private static boolean isAlphanumeric(char c) {
		return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9');
	}

	public static String emailWord(String emailWordInput) {
		StringBuilder stringBuilder = new StringBuilder();
		if (!emailWordInput.isEmpty()) {
			char c0 = emailWordInput.charAt(0);
			if ('a' <= c0 && c0 <= 'z' || 'A' <= c0 && c0 <= 'Z') {
				stringBuilder.append(c0);
				for (int i = 1; i < emailWordInput.length(); i++) {
					char c1 = emailWordInput.charAt(i);
					if (c1 == '_' || c1 == '.') {
						// peek to make sure an alphanumeric character follows
						if (i < emailWordInput.length() - 1) {
							char c2 = emailWordInput.charAt(i + 1);
							if (isAlphanumeric(c2)) {
								stringBuilder.append('_');
							} else {
								// throw c away; an alpha numeric must follow '_'
							}
						} else {
							// throw c away; last char can't be '_' or '.'
						}
					} else if (isAlphanumeric(c1)) {
						stringBuilder.append(c1);
					} else {
						// peek to make sure an alphanumeric character follows
						if (i < emailWordInput.length() - 1) {
							char c3 = emailWordInput.charAt(i + 1);
							if (isAlphanumeric(c3)) {
								stringBuilder.append('_');
							}
						} else {
							// throw c away
						}
					}
				}
				String result = stringBuilder.toString();
				if (!result.isEmpty()) {
					return result;
				} else {
					throw new MalformedEmailWordInputException(emailWordInput);
				}
			} else {
				throw new MalformedEmailWordInputException(emailWordInput);
			}
		} else {
			throw new MalformedEmailWordInputException(emailWordInput);
		}
	}

}
