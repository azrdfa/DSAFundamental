package algorithms.sorting.effective;

import java.util.ArrayList;
import java.util.List;

import algorithms.sorting.SortAlgorithm;

public class MergeSort extends SortAlgorithm {

    @Override
    protected List<Integer> sort(List<Integer> data) {
        return divide(data);
    }

    private List<Integer> merge(List<Integer> arrayA, List<Integer> arrayB) {
        List<Integer> result = new ArrayList<>();
        int pointerA = 0;
        int pointerB = 0;
        while (pointerA < arrayA.size() || pointerB < arrayB.size()) {
            boolean isArrayAEmpty = pointerA >= arrayA.size();
            boolean isArrayBEmpty = pointerB >= arrayB.size();
            if (isArrayAEmpty) {
                result.add(arrayB.get(pointerB));
                pointerB++;
                continue;
            }

            if (isArrayBEmpty) {
                result.add(arrayA.get(pointerA));
                pointerA++;
                continue;
            }

            boolean isItemASmaller = arrayA.get(pointerA) < arrayB.get(pointerB);
            if (isItemASmaller) {
                result.add(arrayA.get(pointerA));
                pointerA++;
            } else {
                result.add(arrayB.get(pointerB));
                pointerB++;
            }
        }

        return result;
    };

    private List<Integer> divide(List<Integer> data) {
        if (data.size() > 1) {
            List<Integer> firstHalf = new ArrayList<>(data.subList(0, data.size()/2));
            List<Integer> secondHalf = new ArrayList<>(data.subList(data.size()/2, data.size()));

            List<Integer> dividedFirstHalf = divide(firstHalf);
            List<Integer> dividedSecondHalf = divide(secondHalf);

            return merge(dividedFirstHalf, dividedSecondHalf);
        } else {
            return data;
        }
    }

    public static void main(String[] args) {
        MergeSort mergeSort = new MergeSort();
        mergeSort.run();
    }

}
