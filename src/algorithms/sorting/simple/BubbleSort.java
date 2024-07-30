package algorithms.sorting.simple;

import java.util.Collections;
import java.util.List;

import algorithms.sorting.SortAlgorithm;

public class BubbleSort extends SortAlgorithm {

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        bubbleSort.run();
    }

    @Override
    protected List<Integer> sort(List<Integer> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            for (int j = 0; j < data.size() - 1 - i; j++) {
                if (data.get(j) > data.get(j + 1)) {
                    Collections.swap(data, j, j + 1);
                }
            }
        }

        return data;
    }
}
