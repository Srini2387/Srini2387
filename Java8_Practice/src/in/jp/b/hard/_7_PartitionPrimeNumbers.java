package in.jp.b.hard;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/*
 * 
 * Partition numbers into prime and non-prime numbers.
 */
public class _7_PartitionPrimeNumbers {

    // Approach 1: Using IntStream
    public static Map<Boolean, List<Integer>> partitionPrimeV1(
            List<Integer> numbers) {

        return numbers.stream()
                .collect(Collectors.partitioningBy(n ->
                    n > 1 &&
                    IntStream.rangeClosed(2, (int) Math.sqrt(n))
                            .noneMatch(i -> n % i == 0)
                ));
    }

    // Approach 2: Optimized prime checking
    public static Map<Boolean, List<Integer>> partitionPrimeV2(
            List<Integer> numbers) {

        return numbers.stream()
                .collect(Collectors.partitioningBy(
                		_7_PartitionPrimeNumbers::isPrime
                ));
    }

    // Reusable optimized prime checker
    public static boolean isPrime(int n) {

        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        List<Integer> numbers =
                Arrays.asList(2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        Map<Boolean, List<Integer>> result1 =
                partitionPrimeV1(numbers);

        Map<Boolean, List<Integer>> result2 =
                partitionPrimeV2(numbers);

        System.out.println("Approach 1:");
        System.out.println("Prime: " + result1.get(true));
        System.out.println("Non-Prime: " + result1.get(false));

        System.out.println("\nApproach 2:");
        System.out.println("Prime: " + result2.get(true));
        System.out.println("Non-Prime: " + result2.get(false));
    }
}
