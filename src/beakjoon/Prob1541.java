package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob1541 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int[] nums;
    static int num_cnt = 0;

    public static void main(String[] args) throws IOException {
        String str = br.readLine();
        nums = new int[str.length()];
        String[] split_minus = str.split("-");
        for (String e : split_minus) {
            // 두 가지 방법이 있다.
            String[] toAdd = e.split("[+]");
            // String[] toAdd = e.split("\\+");
            if (toAdd.length == 1) {
                nums[num_cnt++] = Integer.parseInt(toAdd[0]);
            } else {
                int sum = 0;
                for (int i = 0; i < toAdd.length; i++) {
                    sum += Integer.parseInt(toAdd[i]);
                }
                nums[num_cnt++] = sum;
            }
        }

        int res = nums[0];
        for (int i = 1; i < num_cnt; i++) {
            res -= nums[i];
        }
        System.out.println(res);
    }
}
