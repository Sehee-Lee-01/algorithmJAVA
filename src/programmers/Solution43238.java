package programmers;

import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        Arrays.sort(times);

        long st = 1L;
        long en = (long) n * times[times.length - 1]; // long 형변환을 해줘야 된다...

        while (st != en) {
            long mid = st / 2 + en / 2;
            boolean res = isPossible(mid, n, times);
            if (!res) { // 시간이 적은 경우
                st = mid + 1;
            } else { // 시간이 충분한 경우
                en = mid;
            }
        }

        return st;
    }

    boolean isPossible(long mid, int n, int[] times) {
        long nLong = (long) n;
        for (int time : times) {
            nLong -= mid / time;
            if (nLong <= 0)
                break;
        }

        return (nLong <= 0) ? true : false;
    }
}

// 1차 풀이
// 17:50
// import java.util.*;

// class Solution {
// static class Wait{
// long time;
// long endTime;
// Wait(long time, long endTime) {
// this.time = time;
// this.endTime = endTime;
// }

// void addTimeAtEndTime() {
// this.endTime += this.time;
// }
// }

// public long solution(int n, int[] times) {
// PriorityQueue<Wait> pq = new PriorityQueue<>((a,b) -> (int)
// (a.endTime + a.time - b.endTime - b.time));
// int idx = 0;
// Arrays.sort(times);

// for (int i=0;i<n;i++) {
// if (pq.isEmpty()) {
// pq.offer(new Wait(times[idx], times[idx++]));
// continue;
// }

// if (idx < times.length && times[idx] <= pq.peek().endTime + pq.peek().time) {
// pq.offer(new Wait(times[idx], times[idx++]));
// } else {
// Wait curr = pq.poll();
// curr.addTimeAtEndTime();
// pq.offer(curr);
// }
// }

// long answer = 0;
// while(!pq.isEmpty()) {
// Wait curr = pq.poll();
// if (curr.endTime > answer)
// answer = curr.endTime;
// }

// return answer;
// }
// }