package com.urise.webapp;

import java.util.Arrays;
import java.util.stream.IntStream;

public class HW12 {
    public static void main(String[] args) {
        int[] array = new int[]{9, 1, 3, 5, 1, 3, 5};
        System.out.println(minValue(array));
    }

    public static int minValue(int[] arr) {
        int[] resultArray = Arrays.stream(arr).sorted().distinct().toArray();
        return IntStream.range(0, resultArray.length)
                .map(i -> (int) (Math.pow(10, resultArray.length - 1 - i) * resultArray[i]))
                .sum();
    }
}
