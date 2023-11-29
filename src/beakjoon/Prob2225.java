package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 11:01
// 때로는 펜으로 풀어보는 것도 좋은 것 같네요...
public class Prob2225 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int DIV = 1_000_000_000, N, K;
    static long totalSum = 0, dpSum[][];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dpSum = new long[K + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            dpSum[0][i] = 0;
        }

        for (int i = 0; i <= K; i++) {
            dpSum[i][0] = 1;
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dpSum[i][j] = (dpSum[i - 1][j] + dpSum[i][j - 1]) % DIV;
            }
        }

        System.out.println(dpSum[K][N]);
    }
}
