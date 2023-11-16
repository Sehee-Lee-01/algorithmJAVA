package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//10:54 ~ 11:29
public class Prob11501 {
    static int T, N, price[];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        price = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            price[i] = Integer.parseInt(st.nextToken());
        }
    }

    static void seekIncome() {
        long sum = 0; // int 범위 조심, 큰 수가 나올 땐 long을 사용하자.
        int maxPrice = 0;
        for (int i = N - 1; i > 0; i--) {
            if (maxPrice < price[i]) {
                maxPrice = price[i];
            }
            if (maxPrice > price[i - 1]) {
                sum += maxPrice - price[i - 1];
            }
        }
        sb.append(sum).append("\n");
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            input();
            seekIncome();
        }

        System.out.print(sb.toString());
    }
}
