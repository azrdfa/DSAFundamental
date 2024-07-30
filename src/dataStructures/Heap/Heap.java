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

        Node traversingNode = newNode;
        while (traversingNode.getParent() != null) {
            Integer parentVal = traversingNode.getParent().getValue();
            Integer curVal = traversingNode.getValue();

            if (curVal < parentVal) {
                traversingNode.setValue(parentVal);
                traversingNode.getParent().setValue(curVal);
                traversingNode = traversingNode.getParent();
            } else {
                break;
            }
        }
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

        Node traversingNode = root;
        while (traversingNode.hasChild()) {
            Integer curVal = traversingNode.getValue();
            if (traversingNode.getLeftChild() != null && traversingNode.getRightChild() != null) {
                Integer leftVal = traversingNode.getLeftChild().getValue();
                Integer rightVal = traversingNode.getRightChild().getValue();

                if (leftVal < rightVal && leftVal < curVal) {
                    traversingNode.setValue(leftVal);
                    traversingNode.getLeftChild().setValue(curVal);
                    traversingNode = traversingNode.getLeftChild();
                } else if (rightVal < curVal) {
                    traversingNode.setValue(rightVal);
                    traversingNode.getRightChild().setValue(curVal);
                    traversingNode = traversingNode.getRightChild();
                } else {
                    break;
                }
            } else if (traversingNode.getLeftChild() != null) {
                Integer leftVal = traversingNode.getLeftChild().getValue();
                if (leftVal < curVal) {
                    traversingNode.setValue(leftVal);
                    traversingNode.getLeftChild().setValue(curVal);
                    traversingNode = traversingNode.getLeftChild();
                } else {
                    break;
                }
            } else if (traversingNode.getRightChild() != null) {
                Integer rightVal = traversingNode.getRightChild().getValue();
                if (rightVal < curVal) {
                    traversingNode.setValue(rightVal);
                    traversingNode.getRightChild().setValue(curVal);
                    traversingNode = traversingNode.getRightChild();
                } else {
                    break;
                }
            }
        }

        return result;
    }

    // TODO: Implement heapify
}
