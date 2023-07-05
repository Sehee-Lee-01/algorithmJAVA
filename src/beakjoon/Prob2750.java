package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 버블정렬로 풀기
public class Prob2750 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N, nums[];

    static void swap(int idx1, int idx2) {
        int temp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = temp;
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(nums[0]);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < (N - i - 1); j++) {
                if (nums[j] > nums[j + 1])
                    swap(j, j + 1);
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(nums[i]).append("\n");
        }
        System.out.println(sb.toString());
    }
}
