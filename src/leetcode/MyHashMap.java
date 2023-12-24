package leetcode;

class MyHashMap {
    private static final int MAX_SIZE = 10000; // 최대 호출 수로 크기 설정
    Node[] hashTable;

    public MyHashMap() {
        hashTable = new Node[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; i++) {
            hashTable[i] = new Node(-1, -1); // 루트 설정
        }
    }

    public void put(int key, int value) {
        int hashKey = getHashKey(key);

        if (isHashKeyEmpty(hashKey, hashTable)) { // 아예 없는 경우
            hashTable[hashKey].next = new Node(key, value);
            return;
        }

        Node currNode = hashTable[hashKey].next;
        while (currNode != null) {
            if (currNode.key == key) { // 키가 같은 노드가 있는 경우 value만 업데이트
                currNode.value = value;
                return;
            }

            if (currNode.next == null)
                break;

            currNode = currNode.next;
        }

        // 키가 같은 노드가 없는 경우 맨 뒤에 새로 만들기
        currNode.next = new Node(key, value);
    }

    public int get(int key) {
        int hashKey = getHashKey(key);

        if (!isHashKeyEmpty(hashKey, hashTable)) {
            Node currNode = hashTable[hashKey].next;
            while (currNode != null) {
                if (currNode.key == key) { // 키가 같은 노드가 있는 경우
                    return currNode.value;
                }
                currNode = currNode.next;
            }
        }

        return -1; // 없는 경우
    }

    public void remove(int key) {
        int hashKey = getHashKey(key);

        if (!isHashKeyEmpty(hashKey, hashTable)) {
            Node currNode = hashTable[hashKey];

            while (currNode.next != null) {
                if (currNode.next.key == key) { // 다음 노드의 키가 같은 경우
                    currNode.next = currNode.next.next;
                    return;
                }

                currNode = currNode.next;
            }
        }
    }

    static int getHashKey(int key) {
        return key % MAX_SIZE;
    }

    static boolean isHashKeyEmpty(int hashKey, Node[] hashTable) {
        return hashTable[hashKey].next == null;
    }

    static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }
}
