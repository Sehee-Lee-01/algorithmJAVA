package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 선택 정렬로 풀기
public class Prob1427 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static String input;
    static int nums[];

    static void swap(int idx1, int idx2) {
        int tmp = nums[idx1];
        nums[idx1] = nums[idx2];
        nums[idx2] = tmp;
    }

    public static void main(String[] args) throws IOException {
        input = br.readLine();
        nums = new int[input.length()];
        for (int i = 0; i < nums.length; i++)
            nums[i] = input.charAt(i) - '0';

        // 선택 정렬
        for (int i = 0; i < nums.length; i++) {
            int max_idx = i;
            for (int j = i; j < nums.length; j++) {
                if (nums[max_idx] < nums[j]) {
                    max_idx = j;
                }
            }
            swap(i, max_idx);
            sb.append(nums[i]);
        }
        System.out.println(sb.toString());
    }
}
