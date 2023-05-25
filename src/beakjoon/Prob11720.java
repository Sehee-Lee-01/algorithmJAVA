package beakjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Prob11720 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long sum = 0;
        String nums = br.readLine();
        char[] num = nums.toCharArray() // String -> char[]
        for (int i = 0; i < nums.length(); i++) {
            // sum += nums.charAt(i) - '0';
            sum += num[i] - '0';
        }
        System.out.println(sum);
    }
}
