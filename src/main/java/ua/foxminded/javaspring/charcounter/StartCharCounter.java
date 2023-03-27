package ua.foxminded.javaspring.charcounter;

/*This is a char-counter Java application that takes a string and returns the number of unique characters in the string. 
String must have at least one character.
It's a start-class where user will be prompted to enter string. If user didn't write anything, 
it will offer to enter again.
However if user write multiple 'space', it will count those 'space'.*/


import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartCharCounter {

	private static final String DOUBLE_QUOTE = "\"";

	public static void main(final String[] args) {

		Logger logger = LoggerFactory.getLogger(StartCharCounter.class);
		Scanner scanner = new Scanner(System.in);
		logger.info("Enter the string: ");
		String userString = scanner.nextLine();
		while (userString.length() < 1) {

			logger.info("String must have at least one character. Please try again: ");
			userString = scanner.nextLine();

		}
		scanner.close();

		CharCounter charCounter = new CharCounter();
		Map<Character, Integer> charCountMap = charCounter.countUniqueChars(userString);

		for (Entry<Character, Integer> entry : charCountMap.entrySet()) {
			logger.info(DOUBLE_QUOTE + entry.getKey() + DOUBLE_QUOTE + " - " + entry.getValue());
		}
	}
}
