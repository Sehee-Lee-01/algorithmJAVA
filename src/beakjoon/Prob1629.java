package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1629 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long A, B, C, mul_sum[];

    static long mul(long b) {
        if (b == 1)
            return A;

        long res;
        if (b % 2 == 0) {
            res = mul(b / 2);
            res *= res;
        } else {
            res = mul((b - 1) / 2);
            res *= res;
            res %= C;
            res *= A;
        }

        res %= C;
        return res;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        if (A >= C)
            A %= C;

        if (A != 0) {
            System.out.println(mul(B) + "\n");
        } else
            System.out.println("0\n");
    }
}
// 짝수와 제곱 헷갈리지 말자