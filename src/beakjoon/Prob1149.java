package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1149 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, min_cost[][], cost[][];

    static void dp(int idx) {
        if (idx == N)
            return;
        if (idx == 0) {
            for (int i = 0; i < 3; i++) {
                min_cost[i][idx] = cost[idx][i];
            }
            return;
        }
        for (int i = 0; i < 3; i++) {
            int colors[] = new int[2];
            int cnt = 0;
            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    colors[cnt++] = j;
                }
            }

            min_cost[i][idx] = Math.min(min_cost[colors[0]][idx - 1], min_cost[colors[1]][idx - 1]);
            min_cost[i][idx] += cost[idx][i];
        }
    }

    static int seek_min() {
        int min = min_cost[0][N - 1];
        if (min > min_cost[1][N - 1])
            min = min_cost[1][N - 1];
        if (min > min_cost[2][N - 1])
            min = min_cost[2][N - 1];
        return min;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        min_cost = new int[3][N]; // [last house color][last house idx]
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < N; i++)
            dp(i);

        System.out.println(seek_min());
    }
}
