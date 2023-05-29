package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob11724 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] graph = new int[N + 1][N + 1];
        boolean[] visited = new boolean[N + 1];
        int count = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            graph[u][v] = 1;
            graph[v][u] = 1;
        }
        for (int i = 1; i < N + 1; i++) {
            if (!visited[i]) {
                dfs(graph, visited, i);
                count++;
            }
        }
        System.out.println(count);
    }

    static void dfs(int[][] graph, boolean[] visited, int start) {
        if (visited[start])
            return;
        visited[start] = true;
        for (int i = 1; i < graph.length; i++) {
            if (graph[start][i] == 1) {
                dfs(graph, visited, i);
            }
        }
    }
}
