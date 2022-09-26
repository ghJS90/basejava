package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HW12 {
    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 3, 5, 1, 3, 5};
        System.out.println(minValue(array));

        List<Integer> integers = Arrays.asList(9, 1, 3, 2);
        System.out.println(oddOrEven(integers));
    }

    public static int minValue(int[] arr) {
        return Arrays.stream(arr).sorted().distinct().reduce(0,
                (a, b) -> (a * 10) + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        boolean isSumOdd = (integers.stream().mapToInt(Integer::intValue).sum()) % 2 != 0;

        return integers.stream().
                filter(a -> (isSumOdd ^ a % 2 != 0))
                .collect(Collectors.toList());
    }
}












