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
        boolean[] u = new boolean[N];
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(st.nextToken());
            u[i] = false;
        }
        Arrays.sort(m);

        int st = 0, ed = 1, cnt = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            if (u[i])
                continue;
            for (int j = i + 1; j < N; j++) {
                if (u[j])
                    continue;
                if (m[i] + m[j] > M)
                    break;
                if (m[i] + m[j] == M) {
                    cnt++;
                    u[i] = true;
                    u[j] = true;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
