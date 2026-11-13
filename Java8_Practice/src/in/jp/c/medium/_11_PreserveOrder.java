package in.jp.c.medium;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
 * 
 * Flatten a nested list and remove duplicates while preserving order.
 */

public class _11_PreserveOrder {
    public static void main(String[] args) {

        List<List<Integer>> nestedList = Arrays.asList(
            Arrays.asList(1, 2, 3),
            Arrays.asList(2, 3, 4),
            Arrays.asList(4, 5, 6)
        );

        List<Integer> result = nestedList.stream()
                .flatMap(list -> list.stream())
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);
    }
}