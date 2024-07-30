import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dataStructures.Heap.Heap;

public class App {
    public static void main(String[] args) throws Exception {
        Integer[] rawData = {10,9,8,7,6,5,4,3,2,1};
        List<Integer> data = new ArrayList<>(Arrays.asList(rawData));
        Heap heap = new Heap(data);

        List<Integer> sortedData = heap.sort();
        System.out.println(sortedData);
    }
}
