package beakjoon;

import java.io.*;
import java.util.*;

//3:28
public class Prob15650 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;

    static void nm(String res, int max, int cnt) {
        if (cnt == M) {
            sb.append(res).append("\n");
            return;
        }
        for (int i = (max + 1); i <= N; i++) {
            String tmp = res + i + " ";
            nm(tmp, i, cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        String tmp = "";
        nm(tmp, 0, 0);
        System.out.println(sb.toString());
    }
}
