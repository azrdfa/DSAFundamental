package algorithms.sorting.effective;

import java.util.ArrayList;
import java.util.List;

import algorithms.sorting.SortAlgorithm;

public class QuickSort extends SortAlgorithm {

    @Override
    protected List<Integer> sort(List<Integer> data) {
        List<Integer> firstHalf = new ArrayList<>();
        List<Integer> secondHalf = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        Integer pivotIdx = data.size() - 1;
        Integer pivotVal = data.get(pivotIdx);

        for (int i = 0; i < pivotIdx; i++) {
            if (data.get(i) < pivotVal)
                firstHalf.add(data.get(i));
            if (data.get(i) >= pivotVal)
                secondHalf.add(data.get(i));
        }

        if (firstHalf.size() > 0) {
            List<Integer> sortedFirstHalf = sort(firstHalf);
            result.addAll(sortedFirstHalf);
        }

        result.add(pivotVal);

        if (secondHalf.size() > 0) {
            List<Integer> sortedSecondHalf = sort(secondHalf);
            result.addAll(sortedSecondHalf);
        }

        return result;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();
        quickSort.run();
    }

}
