package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob13458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] stuNums = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            stuNums[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long ans = 0;
        for (int stuNum : stuNums) {
            int remain = stuNum - B;
            long sum = 1;

            if (remain > 0) {
                sum += remain / C;
                if (remain % C > 0)
                    sum++;
            }
            // System.out.println("sum:" + sum);
            ans += sum;
        }

        System.out.println(ans);
    }
}
// long 고려하기!!!!!!!!