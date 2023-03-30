package ua.foxminded.javaspring.charcounter;

/*This class is for handle cache. It has 3 main methods, addCache(), loadCache(), saveCache().
saveCache() adds new result of countUniqueChars(). loadCache() loads CACHE_FILE. And saveCache() to CACHE_FILE.*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleCache {

	static Logger logger = LoggerFactory.getLogger(StartCharCounter.class);
	private static HandleCache instance;
	private static final String CACHE_FILE = "charCounterCache.ser";
	static CharCounter charCounter = new CharCounter();
	private static Map<String, Map<Character, Integer>> cache = new LinkedHashMap<>();;

	public static void setCache(Map<String, Map<Character, Integer>> cache) {
		HandleCache.cache = cache;
	}

	public static Map<String, Map<Character, Integer>> getCache() {
		return cache;
	}

	public static synchronized HandleCache getInstance() {
		if (instance == null)
			instance = new HandleCache();
		return instance;
	}


	public static void addCache(String key, Map<Character, Integer> value) {
		getCache().put(key, value);
	}

	public static void loadCache(CharCounter charCounter) {
		File cacheFile = new File(CACHE_FILE);
		if (cacheFile.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
				Map<String, Map<Character, Integer>> cache = (Map<String, Map<Character, Integer>>) ois.readObject();
				setCache(cache);
			} catch (IOException | ClassNotFoundException e) {
				logger.warn("Could not read cache file: " + e.getMessage());
			}
		}
	}

	public static void saveCache(CharCounter charCounter) {
		File cacheFile = new File(CACHE_FILE);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
			oos.writeObject(getCache());
		} catch (IOException e) {
			logger.warn("Could not save cache file: " + e.getMessage());
		}
	}

}
