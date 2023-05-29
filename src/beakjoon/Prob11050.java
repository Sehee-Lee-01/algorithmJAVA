package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob11050 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] dp = new int[11][11];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp[0][0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i][0] = 1;
            dp[i][i] = 1;
            for (int j = 1; j < i; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }
        System.out.println(dp[N][K]);
    }
}
