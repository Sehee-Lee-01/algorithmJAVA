package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//9:10
public class Prob1806 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, S, arr[], minLen = 100001;
    static boolean oneLenFlag = false;

    static void seekShortLen() {
        int st = 0;
        int en = 0; // st와 같을 수 있다

        while (st < N && en < N && st <= en) {
            int len = en - st + 1;
            int subSum = (st == 0) ? arr[en] : arr[en] - arr[st - 1];
            if (subSum < S) {
                en++;
                continue;
            }

            if (len < minLen)
                minLen = len;
            st++;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];

        int sum = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (tmp >= S) {
                System.out.println(1);
                return;
            }
            sum += tmp;
            arr[i] = sum;
        }

        if (arr[N - 1] < S) { // = 실수 조심하자,,
            System.out.println(0);
            return;
        }

        seekShortLen();

        System.out.println(minLen);
    }
}
