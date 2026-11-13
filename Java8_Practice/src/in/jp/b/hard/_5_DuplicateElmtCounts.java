package in.jp.b.hard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
 * 
 * Find duplicate elements and their occurrence counts.
 */
public class _5_DuplicateElmtCounts {

	public static void main(String[] args) {
		List<Integer> numbers =
			    Arrays.asList(10, 20, 30, 10, 20, 10, 40, 50, 20);

			Map<Integer, Long> duplicates = numbers.stream()
			    .collect(Collectors.groupingBy(
			        Function.identity(),
			        Collectors.counting()
			    ))
			    .entrySet()
			    .stream()
			    .filter(entry -> entry.getValue() > 1)
			    .collect(Collectors.toMap(
			    		entry -> entry.getKey(),
			    		entry -> entry.getValue()
			    ));

			System.out.println(duplicates);
	}

}


