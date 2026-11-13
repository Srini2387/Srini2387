package in.jp.c.medium;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * 
 * Find the longest string in a list; handle ties and empty input. 
 */
public class _12_LongestStrings {

	public static void main(String[] args) {
		List<String> names = Arrays.asList(
			    "Java", "Spring", "Python", "Hibernate", "JavaScript", "Kubernetes"
			);
		
		//first longest 
		Optional<String> longest = names.stream()
		        .max((s1, s2) -> Integer.compare(s1.length(), s2.length()));

		System.out.println(longest.orElse("List is empty"));
		
		//wait; but the question is handle ties 
		List<String> longestStrings = names.stream()
		        .collect(Collectors.groupingBy(String::length))
		        .entrySet()
		        .stream()
		        .max(Map.Entry.comparingByKey())
		        .map(entry -> entry.getValue())
		        .orElse(Collections.emptyList());
		System.out.println(longestStrings);
	}
}

