package ua.foxminded.javaspring.charcounter;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartCharCounter {

	static Logger logger = LoggerFactory.getLogger(StartCharCounter.class);
	static CharCounter charCounter = new CharCounter();
	private static final String DOUBLE_QUOTE = "\"";
	private static final String TAB = "\t";
	private static final String DASH_SIGN = " - ";

	public static void main(final String[] args) {
		CharCounter charCounter = new CharCounter();
		Scanner scanner = new Scanner(System.in);

		while (true) {

			logger.info("Enter the string (or \"0\" to quit, \"1\" to load cache): ");
			String userString = scanner.nextLine();
			while (userString.length() < 1) {
				logger.info("String must have at least one character. Please try again: ");
				userString = scanner.nextLine();
			}

			if (userString.equals("0")) {
				scanner.close();
				break;
			}

			if (userString.equals("1")) {
				HandleCache.loadCache(charCounter);
				Map<String, Map<Character, Integer>> cache = HandleCache.getCache();
				logger.debug("Cache contents:");
				for (String key : cache.keySet()) {
					logger.debug("Cache entry for: " + DOUBLE_QUOTE + key + DOUBLE_QUOTE);
					Map<Character, Integer> charCountMap = cache.get(key);
					for (Entry<Character, Integer> entry : charCountMap.entrySet()) {
						logger.debug(TAB + DOUBLE_QUOTE + entry.getKey() + DOUBLE_QUOTE + DASH_SIGN + entry.getValue());
					}
				}
				continue;
			}

			Map<Character, Integer> charCountMap = charCounter.countUniqueChars(userString);

			for (Entry<Character, Integer> entry : charCountMap.entrySet()) {
				logger.info(DOUBLE_QUOTE + entry.getKey() + DOUBLE_QUOTE + DASH_SIGN + entry.getValue());
			}
			HandleCache.saveCache(charCounter);
		}
	}
}
