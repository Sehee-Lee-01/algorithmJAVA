package beakjoon;

import java.io.IOException;
import java.util.Scanner;

public class Prob2018 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(sc.next());
        long sum = 0;
        int cnt = 0;
        int st = 1, ed = 1;
        while (st != N + 1) {
            if (sum < N)
                sum += ed++;
            else if (sum > N)
                sum -= st++;
            else {
                cnt++;
                sum -= st++;
            }
        }
        System.out.println(cnt);
    }
}
