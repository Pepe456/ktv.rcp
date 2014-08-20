package de.andrena.ktv.model.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidation {

	public static boolean validate(String input) {

		if ((input == null) || input.isEmpty()) {
			return false;
		}

		if (containsSpecialCharacters(input)) {
			return false;
		}

		return true;
	}

	private static boolean containsSpecialCharacters(String input) {
		Pattern nonAlphanumericCharacters = Pattern.compile("[^A-Za-z0-9äöüÄÖÜß]");
		Matcher matcher = nonAlphanumericCharacters.matcher(input);
		return matcher.find();
	}

}
