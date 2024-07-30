package algorithms.sorting.simple;

import java.util.List;

import algorithms.sorting.SortAlgorithm;

public class SelectionSort extends SortAlgorithm {

    @Override
    protected List<Integer> sort(List<Integer> data) {
        for (int i = 0; i < data.size() - 1; i++) {
            int minVal = data.get(i);
            int minValIdx = i;
            for (int j = i + 1; j < data.size(); j++) {
                if (data.get(j) < minVal) {
                    minVal = data.get(j);
                    minValIdx = j;
                }
            }
            int tmp = data.get(i);
            data.set(i, minVal);
            data.set(minValIdx, tmp);
        }
        return data;
    }

    public static void main(String[] args) {
        SelectionSort selectionSort = new SelectionSort();
        selectionSort.run();
    }
}
