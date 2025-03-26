package dataStructures.UnionFind;

import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    private List<Integer> parent;
    private List<Integer> rank;

    public UnionFind(Integer size) {
        this.parent = new ArrayList<>();
        this.rank = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            parent.add(i);
            rank.add(0);
        }
    }

    public Integer find(Integer target) {
        if (parent.get(target) != target) {
            parent.set(target, find(parent.get(target)));
        }

        return parent.get(target);
    }

    public void union(Integer targetX, Integer targetY) {
        Integer rootX = find(targetX);
        Integer rootY = find(targetY);

        if (rootX == rootY) return;

        if (rank.get(rootY) > rank.get(rootX)) {
            parent.set(rootX, rootY);
        } else if (rank.get(rootY) < rank.get(rootX)) {
            parent.set(rootY, rootX);
        } else {
            parent.set(rootY, rootX);
            rank.set(rootX, rank.get(rootX) + 1);
        }
    }

    public List<Integer> getParent() {
        return parent;
    }

    public List<Integer> getRank() {
        return rank;
    }
}
