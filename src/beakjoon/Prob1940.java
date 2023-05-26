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
        for (int i = 0; i < N; i++) m[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(m);

        int st = 0, ed = 1, cnt = 0, sum = 0;
        for (int i = 0; i < N; i++) {
            if (m[i] > M)
                break;
            if (m[i] == 0)
                continue;
            for (int j = i + 1; j < N; j++) {
                if (m[j] == 0)
                    continue;
                if (m[i] + m[j] > M)
                    break;
                if (m[i] + m[j] == M) {
                    cnt++;
                    m[i] = 0;
                    m[j] = 0;
                    break;
                }
            }
        }

        System.out.println(cnt);
    }
}
