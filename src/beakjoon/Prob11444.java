package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob11444 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static long N, U[] = { 1, 1, 1, 0 };
    static final long MOD = 1000000007;

    static long[] mul_matrix(long[] m1, long[] m2) {
        long res[] = new long[4];
        // MOD 나눌 수 있을 만큼 최대한 나누자!!!!!
        res[0] = ((m1[0] * m2[0]) % MOD + (m1[1] * m2[2]) % MOD) % MOD;
        res[1] = ((m1[0] * m2[1]) % MOD + (m1[1] * m2[3]) % MOD) % MOD;
        res[2] = ((m1[2] * m2[0]) % MOD + (m1[3] * m2[2]) % MOD) % MOD;
        res[3] = ((m1[2] * m2[1]) % MOD + (m1[3] * m2[3]) % MOD) % MOD;

        return res;
    }

    static long[] fibo(long n) {
        if (n == 1) {
            return U;
        } else if (n == 2) {
            return mul_matrix(U, U);
        }

        if (n % 2 == 1) {
            return mul_matrix(fibo(n - 1), U);
        } else {
            long[] tmp = fibo(n / 2);
            return mul_matrix(tmp, tmp);
        }
    }
    // static long fibo(long n) {
    // long i = 1;
    // long a = 0;
    // long b = 1;
    // while (i <= n) {
    // long tmp = a;
    // a = (a + b) % MOD;
    // b = tmp;
    // i++;
    // }
    // return a;
    // }

    public static void main(String[] args) throws IOException {
        N = Long.parseLong(br.readLine());
        long res[] = fibo(N);
        System.out.println(res[1]);
    }
}
// 행렬이 왜 쓰이는 지 알 것 같은 문제!!!
// 행렬을 알아야 쉽게 접근할 수 있다..
// https://st-lab.tistory.com/252
// 위 링크에서 식을 참고하여 구현해보았다.