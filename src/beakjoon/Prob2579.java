package beakjoon;

import java.io.*;
import java.util.*;

public class Prob2579 {
    static int N;
    static int[] stairs = new int[301];
    static int[] dp_list = new int[301];

    static int dp(int n) {
        if (n < 0) return 0;
        if (n < 2 || dp_list[n] > 0) return dp_list[n];
        dp_list[n] = Math.max(dp(n-2), dp(n-3) + stairs[n-1]) + stairs[n];
        return dp_list[n];
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i=0; i<N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
            dp_list[i] = 0;
        }
        dp_list[0] = stairs[0];
        if (N > 1)
            dp_list[1] = stairs[0] + stairs[1];
        System.out.println(dp(N-1));
    }
}
