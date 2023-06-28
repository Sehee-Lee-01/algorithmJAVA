package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob11053 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, A[], max_len[], max = 0;

    static void dp(int idx) {
        max_len[idx] = 1;
        int toSum = 0;
        for (int i = 0; i < idx; i++) {
            if (A[i] < A[idx] && toSum < max_len[i]) {
                toSum = max_len[i];
            }
        }
        max_len[idx] += toSum;
        if (max_len[idx] > max)
            max = max_len[idx];
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        max_len = new int[N];
        A = new int[N];
        // dp
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            dp(i);
        }

        System.out.println(max);
    }
}
