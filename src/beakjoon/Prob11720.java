package beakjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prob11720 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 안써도 된다.
        long sum = 0;
        String nums = br.readLine();
        char[] num_list = nums.toCharArray(); // String -> char[]
        for (char num: num_list) {
            // sum += nums.charAt(i) - '0';
            sum += num - '0';
        }
        System.out.println(sum);
    }
}
