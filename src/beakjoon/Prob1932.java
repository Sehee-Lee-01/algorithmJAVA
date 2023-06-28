package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1932 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, past[], past_num = 0, curr[], curr_num = 0, max = 0;

    static void dp(int val, int level, int loc) {
        if (level == 1) {
            curr[loc] = val;
            return;
        }
        curr[loc] = val;
        if (loc == 1) {
            curr[loc] += past[loc];
        } else if (level == loc) {
            curr[loc] += past[loc - 1];
        } else {
            int toAdd = past[loc];
            if (toAdd < past[loc - 1])
                toAdd = past[loc - 1];
            curr[loc] += toAdd;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        curr = new int[N + 1];
        past = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= i; j++) {
                int val = Integer.parseInt(st.nextToken());
                dp(val, i, j);
                if ((i == N) && (curr[j] > max)) {
                    max = curr[j];
                }
            }
            past = curr.clone();
            past_num = curr_num;
            curr_num = 0;
        }
        System.out.println(max);
    }
}
// 메모리 초과 발생
// 어레이 두 개로 진행할 수 있었지만, 삼각형 칸의 갯수만큼 어레이를 만들어서 진행해서 시간초과