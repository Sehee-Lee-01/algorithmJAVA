package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob11726 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] memo = new int[1001];

    static int dp(int n) {
        if (memo[n] != 0)
            return memo[n];
        return memo[n] = (dp(n - 1) + dp(n - 2)) % 10007;
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        memo[1] = 1;
        memo[2] = 2;
        System.out.println(dp(N));
    }
}
