package com.hust.soict.tannp.helper;

public class InsertionSort implements NumberSort {

    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        int i, j, key;

        for (i = 1; i < n; ++i) {
            key = arr[i];
            j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1 ] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }
}