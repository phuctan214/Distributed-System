package com.hust.soict.tannp.helper;

public class ArrayUtils {

    public static void swapTwoElements(int[] arr, int pos_1, int pos_2) {
        int temp = arr[pos_1];
        arr[pos_1] = arr[pos_2];
        arr[pos_2] = temp;
    }
}
