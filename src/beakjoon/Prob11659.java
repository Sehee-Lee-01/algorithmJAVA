package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob11659 {
    // 입력이 적을 땐 Scanner, 많을 땐 BufferedReader
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuffer sb = new StringBuffer(); 
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.valueOf(st.nextToken());
        int M = Integer.valueOf(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long sum_list[] = new long[N + 1];
        sum_list[0] = 0;
        for (int i = 1; i <= N; i++)
            sum_list[i] = sum_list[i - 1] + Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            sb.append(sum_list[end] - sum_list[start-1]).append('\n');
        }
        System.out.println(sb);
    }
}
