package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14501 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int[] T, P;

    static long dp(int curr, long income) {
        // System.out.println("curr: " + curr + " income: " + income);
        if (curr >= N)
            return income;
        if ((curr + T[curr]) > N) // 여기를 새로 추가하여 해결
            return dp(curr + 1, income);
        // 위 조건문을 안넣으면 맨위 curr>=N에서 income를 리턴할 때
        // dp(curr + T[curr], income + P[curr]) 에서 income + P[curr]를 그대로 리턴하게 됨.
        return Math.max(dp(curr + 1, income), dp(curr + T[curr], income + P[curr])); // 해당 일을 안했을 때, 했을 때
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        T = new int[N];
        P = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        long ans = dp(0, 0);
        System.out.println(ans);
    }
}
