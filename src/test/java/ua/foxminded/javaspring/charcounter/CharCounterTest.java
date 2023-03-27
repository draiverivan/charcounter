package ua.foxminded.javaspring.charcounter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import ua.foxminded.javaspring.charcounter.CharCounter;

class CharCounterTest {

	private CharCounter charCounter;

	@BeforeEach
	void setup() {
		charCounter = new CharCounter();
	}

	CharCounter charCounter2 = new CharCounter();

	@ParameterizedTest
	@CsvSource({
	// @formatter:off
		"abcbda, a, 2", 
		"abcbda, b, 2", 
		"abcbda, c, 1", 
		"abcbda, d, 1" 
		// @formatter:on
	})
	void countUniqueChars_shouldBeEqual_whenOneWord(String input, char ch, int expectedCount) {
		Map<Character, Integer> result = charCounter.countUniqueChars(input);
		assertEquals(expectedCount, result.get(ch));
	}

	@ParameterizedTest
	@CsvSource({
	// @formatter:off
		"hello world, h, 1", 
		"hello world, e, 1", 
		"hello world, l, 3", 
		"hello world, o, 2",
		"hello world, ' ', 1", 
		"hello world, w, 1", 
		"hello world, r, 1", 
		"hello world, d, 1" })
		// @formatter:on
	void countUniqueChars_shouldBeEqual_whenMuhltipleWords(String input, char ch, int expectedCount) {
		Map<Character, Integer> result = charCounter.countUniqueChars(input);
		assertEquals(expectedCount, result.get(ch));
	}

	@ParameterizedTest
	@CsvSource({
	// @formatter:off
		"GreEn HoRsE, G, 1", 
		"GreEn HoRsE, r, 1", 
		"GreEn HoRsE, e, 1", 
		"GreEn HoRsE, E, 2",
		"GreEn HoRsE, n, 1", 
		"GreEn HoRsE, ' ', 1", 
		"GreEn HoRsE, H, 1", 
		"GreEn HoRsE, o, 1", 
		"GreEn HoRsE, R, 1",
		"GreEn HoRsE, s, 1" 
		// @formatter:on
	})
	void countUniqueChars_shouldBeEqual_whenUpperAndLowerCase(String input, char ch, int expectedCount) {
		Map<Character, Integer> result = charCounter.countUniqueChars(input);
		assertEquals(expectedCount, result.get(ch));
	}
	
	@ParameterizedTest
	@CsvSource({
	// @formatter:off
		"101!!246 3654#%@#$4345, 1, 2", 
		"101!!246 3654#%@#$4345, 0, 1", 
		"101!!246 3654#%@#$4345, !, 2", 
		"101!!246 3654#%@#$4345, 2, 1",
		"101!!246 3654#%@#$4345, 4, 4", 
		"101!!246 3654#%@#$4345, 6, 2", 
		"101!!246 3654#%@#$4345, ' ', 1", 
		"101!!246 3654#%@#$4345, 3, 2", 
		"101!!246 3654#%@#$4345, 5, 2",
		"101!!246 3654#%@#$4345, #, 2", 
		"101!!246 3654#%@#$4345, %, 1",
		"101!!246 3654#%@#$4345, @, 1",
		"101!!246 3654#%@#$4345, $, 1"
		// @formatter:on
	})
	void countUniqueChars_shouldBeEqual_whenOnlySymbolsAndNumbers(String input, char ch, int expectedCount) {
		Map<Character, Integer> result = charCounter.countUniqueChars(input);
		assertEquals(expectedCount, result.get(ch));
	}
	
	@ParameterizedTest
	@CsvSource({
	// @formatter:off
		"Гідроізоляція DryWall33-31, Г, 1", 
		"Гідроізоляція DryWall33-31, і, 3", 
		"Гідроізоляція DryWall33-31, д, 1", 
		"Гідроізоляція DryWall33-31, р, 1",
		"Гідроізоляція DryWall33-31, о, 2", 
		"Гідроізоляція DryWall33-31, з, 1", 
		"Гідроізоляція DryWall33-31, л, 1", 
		"Гідроізоляція DryWall33-31, я, 2", 
		"Гідроізоляція DryWall33-31, ц, 1",
		"Гідроізоляція DryWall33-31, ' ', 1", 
		"Гідроізоляція DryWall33-31, D, 1",
		"Гідроізоляція DryWall33-31, r, 1",
		"Гідроізоляція DryWall33-31, y, 1",
		"Гідроізоляція DryWall33-31, W, 1",
		"Гідроізоляція DryWall33-31, a, 1",
		"Гідроізоляція DryWall33-31, l, 2",
		"Гідроізоляція DryWall33-31, 3, 3",
		"Гідроізоляція DryWall33-31, -, 1",
		"Гідроізоляція DryWall33-31, 1, 1"
		// @formatter:on
	})
	void countUniqueChars_shouldBeEqual_whenCombineLettersAndSymbolsAndNonLatin(String input, char ch, int expectedCount) {
		Map<Character, Integer> result = charCounter.countUniqueChars(input);
		assertEquals(expectedCount, result.get(ch));
	}

	@ParameterizedTest
	@CsvSource({
		// @formatter:off
		"' '' '' '' '' '' ', ' ', 6"
		// @formatter:on
	})
	void countUniqueChars_shouldBeEqual_whenOnlySpaces(String input, char ch, int expectedCount) {
		Map<Character, Integer> result = charCounter.countUniqueChars(input);
		assertEquals(expectedCount, result.get(ch));
	}
	
	@ParameterizedTest
	@EmptySource
	void countUniqueChars_shouldBeEqual_whenEmptyString(String input) {
		CharCounter charCounter2 = new CharCounter();
		Map<Character, Integer> charCountMap = new LinkedHashMap<>();
		assertEquals(charCountMap, charCounter2.countUniqueChars(input));
	}

	@ParameterizedTest
	@NullSource
	void countUniqueChars_shouldThrowsNullPointerException_whenStringNull(String input) {
		assertThrows(NullPointerException.class, () -> charCounter2.countUniqueChars(input));
	}

}
