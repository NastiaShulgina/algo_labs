package com.company;

import java.util.Arrays;


public class HeapSort {
    private final int[] arr;
    private SortOrder order = SortOrder.ASCENDING;
    private static int[] arrSorted;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public void setOrder(SortOrder order) {
        this.order = order;
    }


    public int[] check () {
        arrSorted = arr;
        int length = arrSorted.length;

        if (order == SortOrder.ASCENDING) {
            for (int i = 1; i < length; i++) {
                if (arrSorted[i - 1] > arrSorted[i]) {
                    heapSort();
                    break;
                }
                Statistic.comparisons++;
            }
            } else {
            for (int i = 1; i < length; i++)
            {
                if (arrSorted[i - 1] < arrSorted[i]) {
                    heapSort();
                    break;
                }
                Statistic.comparisons++;
            }
        }
        return arrSorted;
    }

    private void heapSort() {
        long startTime = System.nanoTime();
        int length = arrSorted.length;
        arrSorted = arr;

        for (int i = length / 2 - 1 ; i >= 0; i--) {
            heapify(i, length);
        }

        for (int i = length - 1; i >= 0; i--) {
            swap(0, i);
            heapify(0, i);
        }
        long endTime = System.nanoTime();

        Statistic.exTime = (endTime - startTime) / (double) 1000000;
    }

    private void heapify(int root, int size) {
        int left = root * 2 + 1;
        int right = root * 2 + 2;
        int maxIndex = root;

        if (order == SortOrder.ASCENDING) {
            if (left < size && arrSorted[maxIndex] < arrSorted[left]) {
                maxIndex = left;
                Statistic.comparisons++;
            }

            if (right < size && arrSorted[maxIndex] < arrSorted[right]) {
                maxIndex = right;
                Statistic.comparisons++;
            }
        } else {
            if (left < size && arrSorted[maxIndex] > arrSorted[left]){
                maxIndex = left;
                Statistic.comparisons++;
            }
            if (right < size && arrSorted[maxIndex] > arrSorted[right]) {
                maxIndex = right;
                Statistic.comparisons++;
            }
        }

        if (maxIndex == root)
            return;
        swap(maxIndex, root);
        heapify(maxIndex, size);
    }

    private void swap(int a, int b) {
        int x = arrSorted[a];
        arrSorted[a] = arrSorted[b];
        arrSorted[b] = x;
        Statistic.swaps++;
    }


    static class Statistic {
        private static double exTime;
        private static long swaps = 0;
        private static long comparisons = 0;

        @Override
        public String toString() {
            return "HeapSort: \n" +
                    "Execution time: " + Statistic.exTime + "\n" +
                    "Comparisons " + Statistic.comparisons + "\n" +
                    "Swaps " + Statistic.swaps + "\n" +
                    "Result " + Arrays.toString(arrSorted);
        }
    }

}

