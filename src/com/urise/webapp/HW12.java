package com.urise.webapp;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HW12 {
    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 3, 5, 1, 3, 5};
        List<Integer> integers = Arrays.asList(9, 1, 3, 5, 1, 3, 5, 2, 1);
        System.out.println(minValue(array));
        System.out.println(oddOrEven(integers));
    }

    public static int minValue(int[] arr) {
        return Arrays.stream(arr).sorted().distinct().reduce(0,
                (a, b) -> (a * 10) + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int sum = integers.stream().mapToInt(x -> x).sum();
        System.out.println(sum);
        if (sum % 2 != 0) {
            return integers.stream().filter(a -> a % 2 == 0).collect(Collectors.toList());
        }
        return integers.stream().filter(a -> a % 2 != 0).collect(Collectors.toList());
    }
}


















