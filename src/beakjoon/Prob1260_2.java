package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

//9:16
public class Prob1260_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, V;
    static List<Integer>[] links;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());

        links = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            links[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            links[node1].add(node2);
            links[node2].add(node1);
        }
        dfs(V);
        sb.append("\n");
        bfs(V);

        System.out.println(sb.toString());
    }

    static void dfs(int V) {
        for (int i = 1; i <= N; i++)
            links[i].sort((a, b) -> b - a);

        Stack<Integer> s = new Stack();
        boolean[] visited = setVisited();
        s.push(V);

        while (!s.isEmpty()) {
            int curr = s.pop();
            if (!visited[curr]) {
                visited[curr] = true;
                sb.append(curr).append(" ");

                for (int node : links[curr]) {
                    if (!visited[node]) {
                        s.push(node);
                    }
                }
            }
        }
    }

    static void bfs(int V) {
        for (int i = 1; i <= N; i++)
            links[i].sort((a, b) -> a - b);

        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = setVisited();
        q.add(V);
        visited[V] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr).append(" ");

            for (int node : links[curr]) {
                if (!visited[node]) {
                    visited[node] = true;
                    q.add(node);
                }
            }
        }
    }

    static boolean[] setVisited() {
        boolean[] visited = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            visited[i] = false;
        }
        return visited;
    }
}
