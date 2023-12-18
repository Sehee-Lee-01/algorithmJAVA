package leetcode;

class MyCircularDeque {
    int[] data;
    int front;
    int maxSize;
    int size;

    public MyCircularDeque(int k) {
        data = new int[k];
        front = 0;
        maxSize = k;
        size = 0;
    }

    public boolean insertFront(int value) {
        if (size == maxSize)
            return false;
        front = (front == 0) ? maxSize - 1 : front - 1;
        data[front] = value;
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if (size == maxSize)
            return false;
        if (size == 0)
            data[front] = value;
        else
            data[getRearIdx() == maxSize - 1 ? 0 : getRearIdx() + 1] = value;

        size++;
        return true;
    }

    public boolean deleteFront() {
        if (size == 0)
            return false;

        front = (front == maxSize - 1) ? 0 : front + 1;
        size--;
        return true;
    }

    public boolean deleteLast() {
        if (size == 0)
            return false;

        size--;
        return true;
    }

    public int getFront() {
        if (size == 0)
            return -1;
        return data[front];
    }

    public int getRear() {
        if (size == 0)
            return -1;
        return data[getRearIdx()];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    private int getRearIdx() {
        int rear = front + size - 1;
        return rear > maxSize - 1 ? rear - maxSize : rear;
    }
}