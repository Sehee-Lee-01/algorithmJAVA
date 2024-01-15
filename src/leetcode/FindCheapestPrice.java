package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static final int INF = Integer.MAX_VALUE;
    static List<Edge>[] edges;
    static int[] map;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        edges = new ArrayList[n];
        map = new int[n];
        for (int i = 0; i < n; i++) {
            edges[i] = new ArrayList<>();
            map[i] = INF; // dis
        }

        for (int[] flight : flights) {
            edges[flight[0]].add(new Edge(flight[1], flight[2]));
        }

        bfs(src, dst, k);

        return map[dst] == INF ? -1 : map[dst];
    }

    static void bfs(int src, int dst, int k) {
        Queue<int[]> q = new ArrayDeque();
        map[src] = 0;
        q.add(new int[] { src, 0, -1 });
        while (!q.isEmpty()) {
            int[] curr = q.poll();

            if (curr[2] == k) {
                break;
            }

            for (Edge edge : edges[curr[0]]) {
                if (map[edge.to] > curr[1] + edge.weight) {
                    map[edge.to] = curr[1] + edge.weight;
                    q.add(new int[] { edge.to, map[edge.to], curr[2] + 1 });
                }
            }
        }
    }
}