package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14889 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N; // even
    static int[][] S;
    static long min = -1;

    static void comfirm_diff(boolean[] set) {
        int[][] teams = { new int[N / 2], new int[N / 2] }; // 0: true, 1: false
        int s_num = 0, l_num = 0;

        for (int i = 0; i < N; i++) {
            if (set[i])
                teams[0][s_num++] = i;
            else
                teams[1][l_num++] = i;
        }

        long[] sum = { 0, 0 }; // true, false
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < N / 2 - 1; j++) {
                for (int k = j + 1; k < N / 2; k++) {
                    int idx1 = teams[i][j];
                    int idx2 = teams[i][k];

                    sum[i] += S[idx1][idx2];
                }
            }
        }
        long diff = (sum[0] - sum[1] > 0) ? sum[0] - sum[1] : sum[1] - sum[0];
        if (min < 0)
            min = diff;
        else if (min > diff)
            min = diff;
        return;
    }

    static void make_set(int person, int remain, boolean[] set) {
        // remain은 남은 true 개수
        if (person == N) {
            if (remain == 0) {
                // 차이 구하고 비교하기
                comfirm_diff(set);
            }
            return;
        }
        if (remain < 0)
            return;

        set[person] = true;
        make_set(person + 1, remain - 1, set);
        set[person] = false;
        make_set(person + 1, remain, set);
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        S = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i < j) {
                    S[i][j] += S[j][i];
                }
            }
        }
        boolean[] tmp = new boolean[N];
        make_set(0, N / 2, tmp);
        System.out.println(min);
    }
}
// 팀 조합 방법이 어려웠던 문제..