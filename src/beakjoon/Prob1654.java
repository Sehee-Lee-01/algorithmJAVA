package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 09:08 ~ 10:25
public class Prob1654 {
    static int K, N, maxCurrLen = 1;
    static int[] currK;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static long binarySearch() {
        long start = 1, end = maxCurrLen;

        while (start <= end) {
            long mid = (start + end) / 2; // 탐색 범위에 주의하자...(long)
            long currSum = 0;
            for (int currKLine : currK) {
                currSum += currKLine / mid;
            }

            if (currSum < N) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return end;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        currK = new int[K];
        for (int i = 0; i < K; i++) {
            currK[i] = Integer.parseInt(br.readLine());
            if (maxCurrLen < currK[i])
                maxCurrLen = currK[i];
        }

        System.out.println(binarySearch());
    }

}
