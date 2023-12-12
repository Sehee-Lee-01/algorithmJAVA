package leetcode;

/**
 * Node
 */
class Node {
    public Node(Integer value) {
        this.value = value;
    }

    Node preNode = null;
    Node nexNode = null;
    Integer value;
}

class MyCircularQueue {

    Node root;
    int maxSize;
    int size = 0;

    public MyCircularQueue(int k) {
        root = new Node(null);
        maxSize = k;
        size = 0;
    }

    public boolean enQueue(int value) {
        if (size == maxSize) {
            return false;
        }

        Node newNode = new Node(value);
        if (size == 0) {
            root.nexNode = newNode;
            root.preNode = newNode;
            newNode.nexNode = root;
            newNode.preNode = root;
        } else {
            newNode.nexNode = root;
            newNode.preNode = root.preNode;
            root.preNode.nexNode = newNode;
            root.preNode = newNode;
        }
        size++;
        return true;
    }

    public boolean deQueue() {
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            root.preNode = null;
            root.nexNode = null;
        } else {
            root.nexNode.nexNode.preNode = root;
            root.nexNode = root.nexNode.nexNode;
        }
        size--;
        return true;
    }

    public int Front() {
        if (size == 0) {
            return -1;
        }
        return root.nexNode.value;
    }

    public int Rear() {
        if (size == 0) {
            return -1;
        }
        return root.preNode.value;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }
}