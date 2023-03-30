package ua.foxminded.javaspring.charcounter;

/*This class is for handle cache. It has 3 main methods, addCache(), loadCache(), saveCache().
saveCache() adds new result of countUniqueChars(). loadCache() loads CACHE_FILE. And saveCache() to CACHE_FILE.*/

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HandleCache implements Serializable {

	private static final long serialVersionUID = 1L;
	private static HandleCache instance;

	private HandleCache() {
	}

	Logger logger = LoggerFactory.getLogger(HandleCache.class);
	private static final String CACHE_FILE = "charCounterCache.ser";
	private Map<String, Map<Character, Integer>> cache = new LinkedHashMap<>();

	public Map<String, Map<Character, Integer>> getCache() {
		return cache;
	}

	public static synchronized HandleCache getInstance() {
		if (instance == null)
			instance = new HandleCache();
		return instance;
	}

	public void addCache(String key, Map<Character, Integer> value) {
		getCache().put(key, value);
	}

	public void loadCache() {
		File cacheFile = new File(CACHE_FILE);
		if (cacheFile.exists()) {
			try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
				Map<String, Map<Character, Integer>> cacheLoad = (Map<String, Map<Character, Integer>>) ois
						.readObject();
				this.cache = cacheLoad;
			} catch (IOException | ClassNotFoundException e) {
				logger.warn("Could not read cache file: " + e.getMessage());
			}
		}
	}

	public void saveCache() {
		File cacheFile = new File(CACHE_FILE);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(cacheFile))) {
			oos.writeObject(getCache());
		} catch (IOException e) {
			logger.warn("Could not save cache file: " + e.getMessage());
		}
	}

}
