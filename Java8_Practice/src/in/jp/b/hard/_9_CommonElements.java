package in.jp.b.hard;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 
 * Find common elements between two lists without using nested loops.
 */
public class _9_CommonElements {

	public static void main(String[] args) {
		List<Integer> list1 = Arrays.asList(10, 20, 30, 40, 50);
		List<Integer> list2 = Arrays.asList(30, 40, 50, 60, 70);

		List<Integer> common = list1.stream()
		        .filter(list2::contains)
		        .distinct()
		        .collect(Collectors.toList());

		System.out.println(common);
	}

}
