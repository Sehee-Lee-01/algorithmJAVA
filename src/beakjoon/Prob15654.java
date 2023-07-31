package beakjoon;

// 10:01
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Prob15654 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, nums[];

    static void nm(StringBuilder s, boolean[] _isUse, int cnt, int total) {
        if (total > N) {
            return;
        }

        if (cnt == M) {
            String tmp = s.toString();
            sb.append(tmp).append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (_isUse[i])
                continue;

            // 넣을 때
            boolean isUse[] = _isUse.clone();
            isUse[i] = true;
            StringBuilder tmp = new StringBuilder(s);
            tmp.append(nums[i]).append(" ");
            nm(tmp, isUse, cnt + 1, total + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N];
        boolean isUse[] = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            isUse[i] = false;
        }

        Arrays.sort(nums);

        nm(new StringBuilder(), isUse, 0, 0);
        System.out.println(sb.toString());
    }
}
