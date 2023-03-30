package ua.foxminded.javaspring.charcounter;

/*The CharCounter class uses a HashMap named cache to store the results of previous calls to countUniqueChars. 
The method first checks if the string has already been processed by checking if it is in the cache. If it is, 
it simply returns the cached result. Otherwise, it calculates the character count for the string and stores it 
in the cache for future use.
Outer map uses the string as the key and the inner map stores the character count for that string.
The countUniqueChars method takes a String argument stringToCount, which represents the string to be processed. 
It loops through the characters of the string and counts the number of occurrences of each character using a Map. 
After counting the unique characters, it adds the result to the cache before returning it.*/

import java.util.LinkedHashMap;
import java.util.Map;

public class CharCounter {

	public Map<Character, Integer> countUniqueChars(String stringToCount) {
		if (HandleCache.getCache().containsKey(stringToCount) || stringToCount == null) {
			return HandleCache.getCache().get(stringToCount);
		}

		Map<Character, Integer> charCountMap = new LinkedHashMap<>();
		for (int i = 0; i < stringToCount.length(); i++) {
			char charInString = stringToCount.charAt(i);
			int count = charCountMap.getOrDefault(charInString, 0);
			charCountMap.put(charInString, count + 1);
		}

		HandleCache.addCache(stringToCount, charCountMap);
		return charCountMap;
	}

}
