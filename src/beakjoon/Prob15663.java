package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Prob15663 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static HashSet<String> ss = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    static int N, M, nums[], num_type[], num_cnt[], num_type_cnt = -1;

    static void nm(StringBuilder s, int cnt) {
        if (cnt == M) {
            // 에러 부분1
            String tmp_S = s.toString();
            if (!ss.contains(tmp_S)) {
                ss.add(tmp_S);
                sb.append(tmp_S).append("\n");
            }
            return;
        }

        for (int i = 0; i <= num_type_cnt; i++) {
            if (num_cnt[i] > 0) {
                num_cnt[i]--;
                StringBuilder tmp_s = new StringBuilder(s);
                tmp_s.append(num_type[i]).append(" ");
                nm(tmp_s, cnt + 1);
                num_cnt[i]++;
                // 에러 부분2
                // nm은 여기서 호출을 안해도 된다.
            }
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[N];
        num_type = new int[N];
        num_cnt = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(nums);
        int past = -1;
        for (int i = 0; i < N; i++) {
            if (nums[i] != past) {
                num_type[++num_type_cnt] = nums[i];
                num_cnt[num_type_cnt] = 1;
            } else
                num_cnt[num_type_cnt]++;
            past = nums[i];
        }

        nm(new StringBuilder(), 0);
        System.out.print(sb.toString());
    }
}
// N이 작기 때문에 Map 으로 할 필요없다.
// 사전순 정렬과 숫자 정렬 헷갈리지 말기
// 결과 부분에서 중복만 빼면 된다.
// sorting된 숫자별로 재귀에서 한단계씩 검사한다. 그렇기 때문에 해당 단계에서 그 숫자를 포함하지 않은 nm()을 호출하지 않아도
// 된다.