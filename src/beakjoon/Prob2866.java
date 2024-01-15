package beakjoon;

import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        int res = 0;

        for (int i = 1; i < R; i++) {
            boolean isDup = false;
            Set<String> set = new HashSet<>();
            for (int j = 0; j < C; j++) {
                String s = "";
                for (int k = i; k < R; k++) {
                    s += map[k][j];
                }
                if (set.contains(s)) {
                    isDup = true;
                    break;
                }
            }

            if (isDup)
                break;
            res++;
        }

        System.out.println(res);
    }
}
