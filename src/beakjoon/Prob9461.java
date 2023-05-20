package beakjoon;

import java.util.*;
import java.io.*;

public class Prob9461 {
    static long[] dp = new long[101];
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for (int i = 6; i < 101; i++)
            dp[i] = dp[i - 1] + dp[i - 5];

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append('\n');
        }
        System.out.println(sb.toString());
    }
}
