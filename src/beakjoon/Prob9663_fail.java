package beakjoon;

//02:07
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob9663_fail {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, res;
    static int[] dy = { 1, -1, 0, 0, 1, 1, -1, -1 };
    static int[] dx = { 0, 0, -1, 1, 1, -1, 1, -1 };

    static void draw_map(boolean[][] map) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(map[i][j] ? "Q " : "X ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean[][] draw_queen(boolean[][] map, int y, int x) {
        boolean[][] tmp_map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp_map[i][j] = map[i][j];
            }
        }
        tmp_map[y][x] = true;
        // 상하좌우 대각선
        int dis = 1;
        while (dis < N) {
            for (int i = 0; i < 8; i++) {
                int ny = y + dy[i] * dis;
                int nx = x + dx[i] * dis;
                if (ny < 0 || nx < 0 || ny >= N || nx >= N)
                    continue;
                tmp_map[ny][nx] = true;
            }
            dis++;
        }
        return tmp_map;
    }

    static void check_queen(int rem, int rec_y, boolean map[][]) {
        if (rem == 0) {
            res++;
            return;
        }

        if (rec_y == N - 1)
            return;

        for (int i = rec_y + 1; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j])
                    continue;
                boolean[][] tmp_map = draw_queen(map, i, j);
                check_queen(rem - 1, i, tmp_map);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        boolean[][] map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = false;
            }
        }

        check_queen(N, -1, map);
        System.out.println(res);
    }
}
// 이중 for 문은 깊은 복사를 하려면 일일이 복사해줘야한다.
// 메모리 초과