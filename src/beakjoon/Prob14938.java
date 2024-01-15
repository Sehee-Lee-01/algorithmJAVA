package beakjoon;

import java.io.*;
import java.util.*;

public class Prob14938 {
    static class Edge {
        int from;
        int to;
        int len;

        Edge(int from, int to, int len) {
            this.from = from;
            this.to = to;
            this.len = len;

        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, r;
    static int[] itemCnts;
    static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        itemCnts = new int[n + 1];
        edges = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            itemCnts[i] = Integer.parseInt(st.nextToken());
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(a, b, l));
            edges[b].add(new Edge(b, a, l));
        }

        int res = 0;
        for (int st = 1; st <= n; st++) {
            int tmp = daik(st);
            if (res < tmp) {
                res = tmp;
            }
        }

        System.out.println(res);
    }

    static int daik(int st) {
        int[] dists = new int[n + 1];
        Arrays.fill(dists, Integer.MAX_VALUE);
        dists[st] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>((a, b) -> a.len - b.len);
        pq.add(new Edge(0, st, 0));
        while (!pq.isEmpty()) {
            Edge curr = pq.poll();
            for (Edge e : edges[curr.to]) {
                if (dists[e.to] > dists[curr.to] + e.len && dists[curr.to] + e.len <= m) {
                    dists[e.to] = dists[curr.to] + e.len;
                    pq.add(e);
                }
            }
        }

        int itemCnt = 0;
        for (int i = 1; i <= n; i++) {
            if (dists[i] != Integer.MAX_VALUE) {
                itemCnt += itemCnts[i];
            }
        }

        return itemCnt;
    }
}
