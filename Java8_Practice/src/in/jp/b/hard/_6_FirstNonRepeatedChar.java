package in.jp.b.hard;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * 
 * Find the first non-repeated character in a string.
 */
public class _6_FirstNonRepeatedChar {

	public static void main(String[] args) {
		String str = "swiss";
		
		//method-1
		Character character = str.chars().mapToObj(ch-> (char) ch).filter(ch->str.indexOf(ch)==str.lastIndexOf(ch)).findFirst().get();
		System.out.println(character);

		System.out.println(firstNonRepeated(str)); // w
		
	}
	
	//method-2
	public static Character firstNonRepeated(String str) {
		
		Map<Character, Long> charCount = str.chars()
				.mapToObj(c -> (char) c)
				.collect(Collectors.groupingBy(
						Function.identity(),
						LinkedHashMap::new,
						Collectors.counting()
						));
		
		return charCount.entrySet().stream()
				.filter(entry -> entry.getValue() == 1)
				.map(Map.Entry::getKey)
				.findFirst()
				.orElse(null);
	}

}
