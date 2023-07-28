package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob9465 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, n, stick_score[][], col_score[][];
    static boolean take[][];

    static void sticker(int col) {
        if (col == n)
            return;

        col_score[0][col] = Math.max(col_score[1][col - 1], col_score[2][col - 1]);
        col_score[0][col] += stick_score[0][col];

        col_score[1][col] = Math.max(col_score[0][col - 1], col_score[2][col - 1]);
        col_score[1][col] += stick_score[1][col];

        col_score[2][col] = Math.max(col_score[0][col - 1], col_score[1][col - 1]);
        sticker(col + 1);
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            stick_score = new int[2][n];
            take = new boolean[2][n];
            col_score = new int[3][n]; // 0: up, 1:down
            for (int j = 0; j < 2; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    stick_score[j][k] = Integer.parseInt(st.nextToken());
                    take[j][k] = false;
                }
            }
            col_score[0][0] = stick_score[0][0];
            col_score[1][0] = stick_score[1][0];
            col_score[2][0] = 0;
            sticker(1);
            sb.append(Math.max(col_score[0][n - 1], col_score[1][n - 1])).append("\n");
        }

        System.out.println(sb.toString());
    }
}
// 복붙 조심
// 시간 초과(각 좌표로 했을 때도, 열 기준으로 했을 때도)
// 웬만하면 탑다운 보다는 바텀업 방식을 사용하자!!!
// 좌표가 주어진다고 x,y 일일이 계산하지 말고 묶어서 계산할 수 있는건 뭔지 확인하기