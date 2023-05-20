package beakjoon;

import java.util.*;
import java.io.*;

public class Prob9461 {
    static long[] dp = new long[101];

    static void init() {
        for (int i = 6; i <= 100; i++) dp[i] = 0;
    }
    static long P(int N) {
        if (N <= 0) return 0;
        if (dp[N] != 0) return dp[N];
        return dp[N] = P(N - 1) + P(N - 5);
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder(100000);
        int T = Integer.parseInt(br.readLine());
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            init();
            sb.append(Long.toString(P(N))).append('\n');
        }
        System.out.println(sb);
    }
}
