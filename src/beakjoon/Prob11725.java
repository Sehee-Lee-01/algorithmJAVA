package beakjoon;

import java.util.*;
import java.io.*;

public class Prob11725 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;

    static void findParent(HashMap<Integer, ArrayList<Integer>> map, int[] parents) {
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            visited[i] = false;
        }

        Queue<Integer> q = new LinkedList<>();
        visited[1] = true;
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i : map.get(cur)) {
                if (!visited[i]) {
                    visited[i] = true;
                    parents[i] = cur;
                    q.add(i);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            map.get(n1).add(n2);
            map.get(n2).add(n1);
        }

        int[] parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        findParent(map, parents);

        for (int i = 2; i <= N; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
