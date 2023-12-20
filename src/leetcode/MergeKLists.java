package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (ListNode root : lists) {
            while (root != null) {
                pq.add(root.val);
                root = root.next;
            }
        }
        if (pq.isEmpty())
            return null;
        ListNode root = new ListNode(pq.poll());
        ListNode curr = root;
        while (!pq.isEmpty()) {
            curr.next = new ListNode(pq.poll());
            curr = curr.next;
        }
        return root;
    }
}