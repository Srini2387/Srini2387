package in.jp.b.hard;

import java.util.Arrays;
import java.util.List;

/*
 * 
 * 	Find the third-highest distinct number without sorting the entire list.
 */
public class _4_ThirdHighestSalWithoutSort {

	public static void main(String[] args) { 
		List<Integer> numbers =
			    Arrays.asList(10, 50, 30, 50, 40, 20, 40, 60, 60);

			int[] top = {Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE};

			numbers.stream().distinct().forEach(n -> {
			    if (n > top[0]) {
			        top[2] = top[1];
			        top[1] = top[0];
			        top[0] = n;
			    } else if (n > top[1]) {
			        top[2] = top[1];
			        top[1] = n;
			    } else if (n > top[2]) {
			        top[2] = n;
			    }
			});

			System.out.println(top[0]); 
			System.out.println(top[1]); 
			System.out.println(top[2]);
		
	}

}
