package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob11727 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int memo[] = new int[1001];

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        memo[1] = 1;
        memo[2] = 3;
        memo[3] = 5;

        for (int i = 4; i <= N; i++) {
            memo[i] = (memo[i - 1] + memo[i - 2] * 2) % 10007;
        }
        System.out.println(memo[N]);
    }
}
