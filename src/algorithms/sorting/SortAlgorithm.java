package algorithms.sorting;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import algorithms.utils.ReadSortAlgorithmTestCases;

public abstract class SortAlgorithm {

    protected void run() {
        ReadSortAlgorithmTestCases readSortAlgorithmTestCases = new ReadSortAlgorithmTestCases();
        List<int[]> originalCases = readSortAlgorithmTestCases.getOriginalCases();
        List<int[]> sortedCases = readSortAlgorithmTestCases.getSortedCases();

        boolean isAllMatch = true;
        for (int i = 0; i < originalCases.size(); i++) {
            try {
                List<Integer> res = sort(
                        Arrays.stream(originalCases.get(i)).boxed().collect(Collectors.toList()));
                boolean isMatch = check(res,
                        Arrays.stream(sortedCases.get(i)).boxed().collect(Collectors.toList()));

                if (!isMatch) {
                    String errorMsg = String.format("TEST CASE %s FAILED", i);
                    System.out.println(errorMsg);
                    System.out.println(
                            String.format("res: %s, sortedCase: %s", res, Arrays.toString(sortedCases.get(i))));
                    if (isAllMatch) {
                        isAllMatch = false;
                    }
                }
            } catch (Exception e) {
                System.out.println(
                    String.format("TEST CASE: %s, ERROR: %s", i, e.getLocalizedMessage())
                );
            }
        }

        if (isAllMatch) {
            System.out.println("ALL PASSED!");
        }
    }

    private boolean check(List<Integer> res, List<Integer> actualRes) {
        BiPredicate<List<Integer>, List<Integer>> areLengthsEqual = (arrayA, arrayB) -> arrayA.size() == arrayB.size();

        if (!areLengthsEqual.test(res, actualRes)) {
            System.out.println(String.format("res length: %s, actualRes length: %s", res.size(), actualRes.size()));
            System.out.println("Lengths are not equal");
            return false;
        }

        boolean result = true;

        for (int i = 0; i < res.size(); i++) {
            BiPredicate<Integer, Integer> areValuesMatch = (valueA, valueB) -> valueA.equals(valueB);
            if (!areValuesMatch.test(Integer.valueOf(res.get(i)), Integer.valueOf(actualRes.get(i)))) {
                result = false;
                break;
            }
        }

        return result;
    }

    protected abstract List<Integer> sort(List<Integer> data);
}
