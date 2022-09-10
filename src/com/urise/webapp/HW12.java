package com.urise.webapp;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HW12 {
    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 3, 5, 1, 3, 5};
        System.out.println(minValue(array));
    }

    public static int minValue(int[] arr) {
        return Arrays.stream(arr).sorted().distinct().reduce(0,
                (a, b) -> (a*10)+b);
    }
}
