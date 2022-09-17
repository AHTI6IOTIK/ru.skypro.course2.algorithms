package ru.skypro.course2.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
//        CustomArrayList<String> stringCustomArrayList = new CustomArrayList<>();
//        stringCustomArrayList.add("f");
//        stringCustomArrayList.add("e");
//        stringCustomArrayList.add("d");
//        stringCustomArrayList.add("c");
//        stringCustomArrayList.add("b");
//        stringCustomArrayList.add("a");
//
//        stringCustomArrayList.add(0, "40");
//        System.out.println(stringCustomArrayList.contains("h"));

        /**
         * Test fastSort
         * performance: 0.03861111 min
         */
//        testPerformance(() -> {
//            System.out.println("Test fastSort");
//            int[] ints = generateRandomArray();
//            fastSort(ints, ints.length);
//        });

        /**
         * Test sortInsertion
         * performance: 0.15277778 min
         */
//        testPerformance(() -> {
//            System.out.println("Test sortInsertion");
//            int[] ints = generateRandomArray();
//            sortInsertion(ints);
//        });

        /**
         * Test sortSelection
         * performance: 0.8858334 min
         */
//        testPerformance(() -> {
//            System.out.println("Test sortSelection");
//            int[] ints = generateRandomArray();
//            sortSelection(ints);
//        });

        /**
         * Test sortBubble
         * performance: 3.7830555 min
         */
//        testPerformance(() -> {
//            System.out.println("Test sortBubble");
//            int[] ints = generateRandomArray();
//            sortBubble(ints);
//        });
    }

    private static void testPerformance(CallbackInterface testInterface) {
        long start = System.currentTimeMillis();
        testInterface.call();
        System.out.println("performance: " + ((System.currentTimeMillis() - start) / 60f / 60f) + " min");
    }

    private static int[] fastSort(int[] arr, int size) {
        if (size < 2) {
            return arr;
        }

        int pivot = arr[0];
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            int o = arr[i];
            if (o <= pivot) {
                left.add(o);
            } else {
                right.add(o);
            }
        }

        return Stream.of(
            fastSort(left.stream().mapToInt(Integer::intValue).toArray(), left.size()),
            new int[]{pivot},
            fastSort(right.stream().mapToInt(Integer::intValue).toArray(), right.size())
        ).filter(el -> el.length > 0).mapToInt(i -> i[0]).toArray();
    }

    private static void sortInsertion(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j - 1] >= temp) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = temp;
        }
    }

    private static void sortSelection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minElementIndex]) {
                    minElementIndex = j;
                }
            }
            swapElements(arr, i, minElementIndex);
        }
    }

    private static void sortBubble(int[] arr) {
        System.out.println("arr.length = " + arr.length);
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swapElements(arr, j, j + 1);
                }
            }
        }
    }

    public static int[] generateRandomArray() {
        java.util.Random random = new java.util.Random();
        int[] arr = new int[100_000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(100_000) + 100_000;
        }
        return arr;
    }

    private static void swapElements(int[] arr, int indexA, int indexB) {
        int tmp = arr[indexA];
        arr[indexA] = arr[indexB];
        arr[indexB] = tmp;
    }
}
