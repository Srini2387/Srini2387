package in.jp.b.hard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * 
 * Find the most frequently occurring word in a list of sentences.
 */
public class _8_MostFreqOccuring {

	public static void main(String[] args) {
		List<String> sentences = Arrays.asList(
			    "Java is easy to learn",
			    "Java is powerful",
			    "Java streams are easy",
			    "Streams make Java powerful"
			);

			System.out.println(mostFrequentWord(sentences));
	}
	
	public static String mostFrequentWord(List<String> sentences) {
		
	    return sentences.stream()
	            .flatMap(sentence -> Arrays.stream(sentence.toLowerCase().split("\\s+")))
	            .collect(Collectors.groupingBy(
	                    Function.identity(),
	                    Collectors.counting()
	            ))
	            .entrySet()
	            .stream()
	            .max(Map.Entry.comparingByValue())
	            .map(Map.Entry::getKey)
	            .orElse(null);
	}

}

