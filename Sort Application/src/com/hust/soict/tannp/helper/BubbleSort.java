package com.hust.soict.tannp.helper;

public class BubbleSort implements NumberSort {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; ++i) {
            for (int j = 0; j < n - i - 1; ++j) {
                if (arr[j + 1] < arr[j]) {
                    ArrayUtils.swapTwoElements(arr, j, j + 1);
                }
            }
        }
    }

}
