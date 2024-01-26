package beakjoon;

import java.io.*;
import java.util.*;

public class Prob14938_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, m, r;
    static int[] items;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        items = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(map[i], Integer.MAX_VALUE);
            map[i][i] = 0;
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            map[a][b] = (map[a][b] > l) ? l : map[a][b];
            map[b][a] = (map[b][a] > l) ? l : map[b][a];
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; k <= n; k++) {
                    if (map[j][i] != Integer.MAX_VALUE && map[i][k] != Integer.MAX_VALUE)
                        map[j][k] = Math.min(map[j][k], map[j][i] + map[i][k]);
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = 1; j <= n; j++) {
                if (map[i][j] <= m)
                    sum += items[j];
            }
            res = Math.max(res, sum);
        }

        System.out.println(res);
    }
}
