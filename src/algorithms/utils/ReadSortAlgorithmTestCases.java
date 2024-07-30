package algorithms.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadSortAlgorithmTestCases {

    private final String FILE_NAME = "src/algorithms/testCases/sort_algorithm_test_cases.txt";

    private List<int[]> originalCases = new ArrayList<>();
    private List<int[]> sortedCases = new ArrayList<>();

    public ReadSortAlgorithmTestCases() {
        try {
            List<String> lines = Files.readAllLines(Paths.get(FILE_NAME));

            for (int i = 0; i < lines.size(); i += 2) {
                String originalLine = lines.get(i).replace("Original: ", "").trim();
                String[] originalParts = originalLine.split(" ");
                int[] originalArray = new int[originalParts.length];
                for (int j = 0; j < originalParts.length; j++) {
                    originalArray[j] = Integer.parseInt(originalParts[j]);
                }
                originalCases.add(originalArray);

                String sortedLine = lines.get(i + 1).replace("Sorted: ", "").trim();
                String[] sortedParts = sortedLine.split(" ");
                int[] sortedArray = new int[sortedParts.length];
                for (int j = 0; j < sortedParts.length; j++) {
                    sortedArray[j] = Integer.parseInt(sortedParts[j]);
                }
                sortedCases.add(sortedArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printTestCases() {
        for (int i = 0; i < originalCases.size(); i++) {
            System.out.print("Original: ");
            for (int number : originalCases.get(i)) {
                System.out.print(number + " ");
            }
            System.out.println();

            System.out.print("Sorted: ");
            for (int number : sortedCases.get(i)) {
                System.out.print(number + " ");
            }
            System.out.println();
            System.out.println();
        }
    }

    public List<int[]> getOriginalCases() {
        return originalCases;
    }

    public List<int[]> getSortedCases() {
        return sortedCases;
    }

    public static void main(String[] args) {
        ReadSortAlgorithmTestCases readSortAlgorithmTestCases = new ReadSortAlgorithmTestCases();
        readSortAlgorithmTestCases.printTestCases();
    }
}


