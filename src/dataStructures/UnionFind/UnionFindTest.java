package dataStructures.UnionFind;

public class UnionFindTest {
    public static void main(String[] args) {
        // Inisialisasi UnionFind untuk 10 elemen (0 hingga 9)
        UnionFind uf = new UnionFind(10);

        // Test awal: Semua elemen adalah root dirinya sendiri
        System.out.println("Initial parent: " + uf.getParent());
        System.out.println("Initial rank: " + uf.getRank());

        // Union beberapa elemen
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(4, 5);
        uf.union(5, 6);
        uf.union(6, 7);

        // Setelah union, cek struktur parent
        System.out.println("Parent after unions: " + uf.getParent());
        System.out.println("Rank after unions: " + uf.getRank());

        // Test find (path compression)
        int root1 = uf.find(3);
        int root2 = uf.find(7);

        System.out.println("Root of 3: " + root1);
        System.out.println("Root of 7: " + root2);

        // Pastikan elemen yang terhubung memiliki root yang sama
        assert uf.find(1) == uf.find(3) : "1 and 3 should have the same root!";
        assert uf.find(4) == uf.find(7) : "4 and 7 should have the same root!";

        // Union antara dua set yang berbeda
        uf.union(3, 7);

        // Setelah union, semua elemen harus memiliki root yang sama
        assert uf.find(1) == uf.find(7) : "All elements should be connected!";
        System.out.println("Parent after union of two sets: " + uf.getParent());

        // Test case untuk elemen yang tidak terhubung
        assert uf.find(0) != uf.find(1) : "0 should not be connected to 1!";
        assert uf.find(8) != uf.find(9) : "8 should not be connected to 9!";
        System.out.println("Union-Find structure works correctly.");
    }
}
