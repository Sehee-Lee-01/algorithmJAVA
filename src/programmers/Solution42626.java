package programmers;

import java.util.PriorityQueue;

class Solution {
    public int solution(int[] scoville, int K) {

        int answer = 0;
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int scov : scoville) {
            pq.add(Long.valueOf(scov));
        }

        while (pq.size() > 1 && pq.peek() < K) {
            long food = pq.poll() + (pq.poll() * 2);
            pq.add(food);
            answer++;
        }

        return pq.peek() < K ? -1 : answer;
    }
}