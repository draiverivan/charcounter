package ua.foxminded.javaspring.charcounter;

/*This is a char-counter Java application that takes a string and returns the number of unique characters in the string. 
String must have at least one character.
It's a start-class where user will be prompted to enter string. If user didn't write anything, 
it will offer to enter again.
However if user write multiple 'space', it will count those 'space'.*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StartCharCounter {

	static Logger logger = LoggerFactory.getLogger(StartCharCounter.class);
	private static final String DOUBLE_QUOTE = "\"";
	private static final String TAB = "\t";
	private static final String CACHE_FILE = "charCounterCache.ser";
	private static final String DASH_SIGN = " - ";

	public static void main(final String[] args) {
		CharCounter charCounter = new CharCounter();
		Scanner scanner = new Scanner(System.in);

		while (true) {
			loadCache(charCounter);
			Map<String, Map<Character, Integer>> cache = charCounter.getCache();
			logger.info("Cache contents:");
			for (String key : cache.keySet()) {
				logger.info("Cache entry for: " + DOUBLE_QUOTE + key + DOUBLE_QUOTE);
				Map<Character, Integer> charCountMap = cache.get(key);
				for (Entry<Character, Integer> entry : charCountMap.entrySet()) {
					logger.info(TAB + DOUBLE_QUOTE + entry.getKey() + DOUBLE_QUOTE + DASH_SIGN + entry.getValue());
				}
			}

			logger.info("Enter the string (or \"0\" to quit): ");
			String userString = scanner.nextLine();
			while (userString.length() < 1) {
				logger.info("String must have at least one character. Please try again: ");
				userString = scanner.nextLine();
			}

			if (userString.equals("0")) {
				scanner.close();
				break;
			}

			Map<Character, Integer> charCountMap = charCounter.countUniqueChars(userString);

			for (Entry<Character, Integer> entry : charCountMap.entrySet()) {
				logger.info(DOUBLE_QUOTE + entry.getKey() + DOUBLE_QUOTE + " - " + entry.getValue());
			}
			saveCache(charCounter);
		}
	}

	private static void loadCache(CharCounter charCounter) {
		File cacheFile = new File(CACHE_FILE);
		if (cacheFile.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
				Map<String, Map<Character, Integer>> cache = (Map<String, Map<Character, Integer>>) ois.readObject();
				charCounter.setCache(cache);
			} catch (IOException | ClassNotFoundException e) {
				logger.warn("Could not read cache file: " + e.getMessage());
			}
		}
	}

	private static void saveCache(CharCounter charCounter) {
		File cacheFile = new File(CACHE_FILE);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
			oos.writeObject(charCounter.getCache());
		} catch (IOException e) {
			logger.warn("Could not save cache file: " + e.getMessage());
		}
	}
}
