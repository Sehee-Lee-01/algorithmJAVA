package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob1940 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine()), M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] m = new int[N];
        for (int i = 0; i < N; i++)
            m[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(m);

        int st = 0, ed = N - 1, cnt = 0, sum = 0;
        while (st < ed && st < N && ed < N) {
            sum = m[st] + m[ed];
            if (sum == M) {
                cnt++;
                m[st++] = m[ed--] = 0;
            } else if (sum < M) {
                st++;
            } else {
                ed--;
            }
        }

        System.out.println(cnt);
    }
}
