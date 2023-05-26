package beakjoon;

import java.io.IOException;
import java.util.Scanner;

public class Prob2018 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(sc.next());
        int cnt = 0;
        for (int st = 1; st <= N; st++) {
            long S = 0;
            for (int ed = st; ed <= N; ed++) {
                S += ed;
                if(S > N) break;
                if (S == N) {
                    cnt++;
                    break;
                }
            }
        }
        System.out.println(cnt);
    }
}
