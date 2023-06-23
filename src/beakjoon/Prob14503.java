package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14503 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, r, c, d, clean = 0; // d = 0N, 1E, 2S, 3W
    static int[] dir_y = { 0, 1, 0, -1 }, dir_x = { -1, 0, 1, 0 }; // 서, 남, 동, 북
    static int[][] map; // 0: 청소X, 1: 벽, 2: 청소O으로 하기

    static void print_map() {
        System.out.println("r: " + r + " c: " + c + " d: " + d);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static void clean_rooms() {
        while (true) {
            if (map[r][c] == 0) {
                map[r][c] = 2;
                clean++;
            }
            // print_map();
            // 청소할 벽 있는지 확인
            boolean move = false;
            for (int i = 1; i <= 4; i++) {
                int tmp_d = d + i;
                if (tmp_d > 3)
                    tmp_d %= 4;
                if (map[r + dir_y[tmp_d]][c + dir_x[tmp_d]] == 0) {
                    r += dir_y[tmp_d];
                    c += dir_x[tmp_d];
                    d = tmp_d;
                    move = true;
                    break;
                }
            }
            if (move)
                continue;

            // 4칸이 다 청소되었다면
            r -= dir_y[d];
            c -= dir_x[d];
            if (map[r][c] == 1) {
                break;
            }
        }
        return;
    }

    public static void main(String[] args) throws IOException {
        // input
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = 3 - Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean_rooms();

        // output
        System.out.println(clean);
    }
}
