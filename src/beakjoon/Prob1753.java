package beakjoon;

import java.io.*;
import java.util.*;

class Node {
    int e, v;

    Node(int e, int v) {
        this.e = e;
        this.v = v;
    }
}

public class Prob1753 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    // static HashMap<Integer, Integer>[] path;
    static ArrayList<Node>[] path;
    static int V, E, K, u, v, w, dis[];
    static boolean visited[];

    static void init() {
        dis = new int[V + 1];
        visited = new boolean[V + 1];
        // path = new HashMap[V + 1];
        path = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            dis[i] = Integer.MAX_VALUE;
            visited[i] = false;
            // path[i] = new HashMap<Integer, Integer>();
            path[i] = new ArrayList<Node>();
        }
        dis[K] = 0;
    }

    static void make_result() {
        for (int i = 1; i <= V; i++) {
            if (dis[i] != Integer.MAX_VALUE) {
                sb.append(dis[i]).append("\n");
            } else {
                sb.append("INF\n");
            }
        }
    }

    static int seek_next() {
        int idx = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= V; i++) {
            if (!visited[i] && (min > dis[i])) {
                idx = i;
                min = dis[i];
            }
        }
        return idx;
    }

    static void daik() {
        Queue<Integer> q = new LinkedList<>();
        q.add(K);

        while (!q.isEmpty()) {
            int curr = q.poll();
            visited[curr] = true;
            // HashMap<Integer, Integer> connect = path[curr];
            // for (Map.Entry<Integer, Integer> e : connect.entrySet()) {
            // if (dis[e.getKey()] > dis[curr] + e.getValue())
            // dis[e.getKey()] = dis[curr] + e.getValue();
            // }
            ArrayList<Node> connect = path[curr];
            for (int i = 0; i < connect.size(); i++) {
                Node nd = path[curr].get(i);
                if (dis[nd.e] > dis[curr] + nd.v) {
                    dis[nd.e] = dis[curr] + nd.v;
                }
            }

            int next = seek_next();
            if (next != 0)
                q.add(next);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        K = Integer.parseInt(br.readLine());
        init();

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            u = Integer.parseInt(st.nextToken());
            v = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            // int value = path[u].getOrDefault(v, -1);
            // if ((value == -1) || (value > w)) {
            // path[u].put(v, w);
            // }
            path[u].add(new Node(v, w));
        }

        daik();

        make_result();
        System.out.println(sb.toString());
    }
}
// Hashmap 시간초과, 메모리 초과
// Linkedlist 통괴
