package leetcode;

import java.util.*;

class Solution {
    static class Edge {
        int num;
        int dis;

        Edge(int num, int dis) {
            this.num = num;
            this.dis = dis;
        }

    }

    public int networkDelayTime(int[][] times, int n, int k) {
        int[] dis = new int[n + 1];
        List<Edge>[] edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dis[i] = -1;
            edges[i] = new ArrayList<>();
        }

        for (int[] time : times) {
            edges[time[0]].add(new Edge(time[1], time[2]));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        pq.add(new Edge(k, 0));
        dis[k] = 0;
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            for (Edge edge : edges[curr.num]) {
                if (dis[edge.num] == -1 || (dis[edge.num] > dis[curr.num] + edge.dis)) {
                    dis[edge.num] = dis[curr.num] + edge.dis;
                    pq.add(edge);
                }
            }
        }

        int res = 0;
        boolean isImpossible = false;
        for (int minDis : dis) {
            if (minDis == -1) {
                isImpossible = true;
                break;
            }
            if (minDis > res)
                res = minDis;
        }

        return isImpossible ? -1 : res;
    }
}