package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob2193 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static long[] dpArr;
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        dpArr = new long[N + 1];
        dpArr[0] = 0;
        dpArr[1] = 1;
        if (N != 1) {
            dpArr[2] = 1;
        }

        System.out.println(dp(N));
    }

    static long dp(int idx) {
        if (idx == 0 || dpArr[idx] != 0)
            return dpArr[idx];
        dpArr[idx] = dp(idx - 1) + dp(idx - 2);
        return dpArr[idx];
    }
}
