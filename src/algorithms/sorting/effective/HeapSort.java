package algorithms.sorting.effective;

import java.util.List;

import algorithms.sorting.SortAlgorithm;
import dataStructures.Heap.Heap;

public class HeapSort extends SortAlgorithm {

    @Override
    protected List<Integer> sort(List<Integer> data) {
        Heap heap = new Heap(data);
        return heap.sort();
    }

    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        heapSort.run();
    }

}
