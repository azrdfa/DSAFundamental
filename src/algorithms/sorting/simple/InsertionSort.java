package algorithms.sorting.simple;

import java.util.Collections;
import java.util.List;

import algorithms.sorting.SortAlgorithm;

public class InsertionSort extends SortAlgorithm {

    @Override
    protected List<Integer> sort(List<Integer> data) {
        for (int i = 1; i < data.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (data.get(j) < data.get(j - 1)) {
                    Collections.swap(data, j, j - 1);
                }
            }
        }
        return data;
    }

    public static void main(String[] args) {
        InsertionSort insertionSort = new InsertionSort();
        insertionSort.run();
    }

}