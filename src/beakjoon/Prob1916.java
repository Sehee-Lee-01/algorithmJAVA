package beakjoon;

import java.io.*;
import java.util.*;

public class Prob1916 {
    static class Edge {
        int num;
        int weight;

        Edge(int num, int weight) {
            this.num = num;
            this.weight = weight;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static List<Edge>[] edges;
    static int[] weights;
    static int N, M, start, end;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        edges = new ArrayList[N + 1];
        weights = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
            weights[i] = Integer.MAX_VALUE;
        }

        M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[s].add(new Edge(e, w));
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        System.out.println(daikstra());
    }

    static long daikstra() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        pq.offer(new Edge(start, 0));

        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            if (weights[curr.num] <= curr.weight)
                continue;

            weights[curr.num] = curr.weight;
            if (curr.num == end) {
                return weights[end];
            }

            for (Edge e : edges[curr.num]) {
                if (weights[e.num] > weights[curr.num] + e.weight) {
                    pq.add(new Edge(e.num, weights[curr.num] + e.weight));
                }
            }
        }

        return weights[end];
    }
}
