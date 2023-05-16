package beakjoon;

import java.io.*;
import java.util.*;

public class Prob1260 {
    static void dfs (boolean[][] graph, boolean[] visited, StringBuilder sb, int start) {
        ArrayList<Integer> st = new ArrayList<>();
        st.add(start);
        while(!st.isEmpty()) {
            int n =  st.remove(st.size()-1);
            if (visited[n]) continue;
            visited[n] = true;
            sb.append(n).append(" ");
            for (int i=graph.length-1; i>=0; i--) {
                if (graph[n][i] && !visited[i]) {
                    st.add(i);
                }
            }
        }
    }
    static void bfs(boolean[][] graph, boolean[] visited, StringBuilder sb, int start) {
        ArrayList<Integer> qu = new ArrayList<>();
        qu.add(start);
        while(!qu.isEmpty()) {
            int n =  qu.remove(0);
            if (visited[n]) continue;
            visited[n] = true;
            sb.append(n).append(" ");
            for (int i=0; i<graph.length; i++) {
                if (graph[n][i] && !visited[i]) {
                    qu.add(i);
                }
            }
        }
    }
    public static void main(String[] args) throws IOException{
        // for input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        //for output
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        boolean[][] graph = new boolean[N+1][N+1];
        boolean[] dfs_visited = new boolean[N+1];
        boolean[] bfs_visited = new boolean[N+1];
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            
            graph[n1][n2] = true;
            graph[n2][n1] = true;
        }

        dfs(graph, dfs_visited, sb, V);
        sb.append("\n");
        bfs(graph, bfs_visited, sb, V);
        sb.append("\n");
        System.out.println(sb);
    }
}
