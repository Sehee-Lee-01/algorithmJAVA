package beakjoon;

import java.io.*;

public class Prob9663 {
    static int N, res;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static boolean is_possible(int[] x_cor, int row) {
        for (int i = 0; i < row; i++) {
            if (x_cor[i] == x_cor[row])
                return false;
            if (Math.abs(x_cor[i] - x_cor[row]) == Math.abs(i - row))
                return false;
        }
        return true;
    }

    static void check_queen(int[] x_cor, int row) {
        if (row == N) {
            res++;
            return;
        }
        // 해당 층에 있을 수 있는 자리가 있는지 확인
        for (int tmp_x = 0; tmp_x < N; tmp_x++) {
            // 어차피 매번 바뀔 것이니 복사할 필요 없음
            x_cor[row] = tmp_x;
            if (is_possible(x_cor, row)) {
                check_queen(x_cor, row + 1);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        int[] x_cor = new int[N];
        check_queen(x_cor, 0);
        System.out.println(res);
    }
}
// 꼭 map이 나와도 2차원 배열이 필요하지 않은 경우가 있다.
// 복사를 줄이자