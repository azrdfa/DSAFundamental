package dataStructures.Heap;

public class Node {
    private Integer value;
    private Node parent;
    private Node leftChild;
    private Node rightChild;

    Node(Integer val) {
        value = val;
    }

    public boolean hasChild() {
        return leftChild != null || rightChild != null;
    }

    public static Node getSibling(Node node) {
        if (node != node.getParent().getLeftChild()) return node.getParent().getRightChild();
        return node.getParent().getRightChild();
    }

    public Integer getValue() {
        return this.value;
    }

    public void setValue(Integer val) {
        this.value = val;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node node) {
        this.parent = node;
    }

    public Node getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(Node node) {
        this.leftChild = node;
    }

    public Node getRightChild() {
        return rightChild;
    }

    public void setRightChild(Node node) {
        this.rightChild = node;
    }
}
