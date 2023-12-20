package programmers;

import java.util.PriorityQueue;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0])
                return a[1] - b[1];
            return a[0] - b[0];
        });

        for (int[] job : jobs) {
            pq.add(job);
        }

        int answer = 0;
        int time = 0;
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });
        while (!pq.isEmpty() || !pq2.isEmpty()) {
            int[] curr = pq2.isEmpty() ? pq.poll() : pq2.poll();
            if (time <= curr[0]) {
                answer += curr[1];
                time = curr[0] + curr[1];
            } else {
                answer += curr[1] + time - curr[0];
                time += curr[1];
            }
            while (!pq.isEmpty() && pq.peek()[0] <= time) {
                pq2.add(pq.poll());
            }

        }

        return answer / jobs.length;
    }
}