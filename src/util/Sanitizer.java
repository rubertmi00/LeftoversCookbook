package util;

public class Sanitizer {
	public static String sanitizeInput(String input) {
		return input.replace(";", "").replace("(", "").replace(")", "");
	}
}
