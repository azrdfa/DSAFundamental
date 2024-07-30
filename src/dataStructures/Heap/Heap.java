package dataStructures.Heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Heap {
    private Node root;

    public Heap(List<Integer> data) {
        while (!data.isEmpty()) {
            add(root, data.get(0));
            data.remove(0);
        }
    }

    public List<Integer> sort() {
        List<Integer> result = new ArrayList<>();
        while (root != null) {
            Integer deleteRes = delete();
            result.add(deleteRes);
        }
        return result;
    }

    public void printTree(Node root, int space) {
        // Base case
        if (root == null)
            return;

        // Increase distance between levels
        space += 10;

        // Process right child first
        printTree(root.getRightChild(), space);

        // Print current node after space
        System.out.print(" ");
        for (int i = 10; i < space; i++)
            System.out.print(" ");
        System.out.print(root.getValue() + "\n");

        // Process left child
        printTree(root.getLeftChild(), space);
    }

    private Node getLastElement() {
        if (root == null) {
            return null;
        }

        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();
            stack.push(curNode);

            if (!(curNode.getLeftChild() == null)) {
                queue.add(curNode.getLeftChild());
            }

            if (!(curNode.getRightChild() == null)) {
                queue.add(curNode.getRightChild());
            }
        }

        return stack.peek();
    }

    private Integer getHeight() {
        if (root == null) {
            return 0;
        }

        Node curNode = root;
        Integer height = 1;
        while (!(curNode.getLeftChild() == null)) {
            curNode = curNode.getLeftChild();
            height++;
        }

        return height;
    }

    private void add(Node node, Integer val) {
        Node newNode = new Node(val);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curNode = queue.poll();

            if (!(curNode.getLeftChild() == null)) {
                queue.add(curNode.getLeftChild());
            } else {
                curNode.setLeftChild(newNode);
                newNode.setParent(curNode);
                break;
            }

            if (!(curNode.getRightChild() == null)) {
                queue.add(curNode.getRightChild());
            } else {
                curNode.setRightChild(newNode);
                newNode.setParent(curNode);
                break;
            }
        }

        upwardHeapify(newNode);
    }

    private Integer delete() {
        Integer result = root.getValue();

        if (getHeight() == 1) {
            root = null;
            return result;
        }

        Node lastElement = getLastElement();
        root.setValue(lastElement.getValue());

        if (lastElement.getParent().getRightChild() != null) {
            lastElement.getParent().setRightChild(null);
        } else {
            if (lastElement.getParent() != null) {
                lastElement.getParent().setLeftChild(null);
            } else {
                root.setLeftChild(null);
            }
        }

        downwardHeapify(root);

        return result;
    }

    private static void swapNodeValue(Node firstNode, Node secondNode) {
        Integer temporaryValue = firstNode.getValue();
        firstNode.setValue(secondNode.getValue());
        secondNode.setValue(temporaryValue);
    }

    private void upwardHeapify(Node node) {
        while (node.getParent() != null) {
            Integer parentValue = node.getParent().getValue();
            Integer nodeValue = node.getValue();

            if (nodeValue > parentValue) break;

            swapNodeValue(node, node.getParent());
            node = node.getParent();
        }
    }

    private void downwardHeapify(Node node) {
        while (node.hasChild()) {
            Node leftChild, rightChild;
            Integer nodeValue, leftChildValue, rightChildValue;
            boolean isLeftSmaller, isChildSmaller;

            leftChild = node.getLeftChild();
            rightChild = node.getRightChild();
            nodeValue = node.getValue();

            if (leftChild != null && rightChild != null) {
                leftChildValue = leftChild.getValue();
                rightChildValue = rightChild.getValue();
                isLeftSmaller = leftChildValue < rightChildValue;
                isChildSmaller = isLeftSmaller ? leftChildValue < nodeValue : rightChildValue < nodeValue;

                if (!isChildSmaller) break;
                if (isLeftSmaller) {
                    swapNodeValue(node, leftChild);
                    node = leftChild;
                };
                if (!isLeftSmaller) {
                    swapNodeValue(node, rightChild);
                    node = rightChild;
                };
            } else if (leftChild != null) {
                leftChildValue = leftChild.getValue();
                isChildSmaller = leftChildValue < nodeValue;

                if (!isChildSmaller) break;
                swapNodeValue(node, leftChild);
                node = leftChild;
            } else if (rightChild != null) {
                rightChildValue = rightChild.getValue();
                isChildSmaller = rightChildValue < nodeValue;

                if (!isChildSmaller) break;
                swapNodeValue(node, rightChild);
                node = rightChild;
            }
        }
    }
}
