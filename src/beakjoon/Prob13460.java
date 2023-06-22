package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob13460 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static char[][] map;

    static int bfs(int[] loc) {

        Queue<int[]> toVisiList = new LinkedList<>();
        toVisiList.add(loc);
        int[] y_dir = { -1, 1, 0, 0 };
        int[] x_dir = { 0, 0, -1, 1 };

        while (!toVisiList.isEmpty()) {
            int[] curr = toVisiList.remove();
            int cnt = curr[4];

            if (cnt > 9) // 이 녀석을 cnt>10으로 해놓고 있었다.
                continue;

            // System.out.println("curr[0]: " + curr[0] + ", curr[1]: " + curr[1]);

            for (int i = 0; i < 4; i++) {
                int red_y = curr[0], red_x = curr[1], blu_y = curr[2], blu_x = curr[3];
                boolean red_block = false, blu_block = false, red_goal = false, pass = true;

                // 벽과 인접 or 구멍에 빠졌거나(두 구슬 모두)
                while (!red_block || !blu_block) {
                    if (!red_goal && map[red_y + y_dir[i]][red_x + x_dir[i]] == 'O') {
                        // 한 칸 더 가면 구멍에 빠질 때
                        red_goal = true;
                        red_block = true;
                        red_y += y_dir[i];
                        red_x += x_dir[i];
                    } else if (!red_goal && (map[red_y + y_dir[i]][red_x + x_dir[i]] == '.')
                            && !((red_y + y_dir[i] == blu_y) && (red_x + x_dir[i] == blu_x))) {
                        // 아직 구멍에 안빠졌고, 한 칸 더 갈 수 있고, 앞에 공이 없을 때
                        red_block = false;
                        red_y += y_dir[i];
                        red_x += x_dir[i];
                    } else // 구멍에 빠졌거나 앞이 막혀있을 때
                        red_block = true;

                    // non-pass: 파란공이 빠진 경우
                    if (map[blu_y + y_dir[i]][blu_x + x_dir[i]] == 'O') {
                        // 한 칸 더 가면 구멍에 빠질 때
                        pass = false;
                        break;
                    } else if (map[blu_y + y_dir[i]][blu_x + x_dir[i]] == '.'
                            && !((blu_y + y_dir[i] == red_y) && (blu_x + x_dir[i] == red_x))) {
                        // 아직 구멍에 안빠졌고, 한 칸 더 갈 수 있고, 앞에 공이 없을 때
                        blu_block = false;
                        blu_y += y_dir[i];
                        blu_x += x_dir[i];
                    } else // 구멍에 빠졌거나 앞이 막혀있을 때
                        blu_block = true;
                }

                if (pass && !(red_y == curr[0] && red_x == curr[1] && blu_y == curr[2] && blu_x == curr[3])) {
                    // 패스 되었고 하나라도 움직인 경우
                    if (red_goal) // 빨간공이 구멍에 들어간 경우
                        return cnt + 1;
                    // 패스지만 구멍에 안들어간 경우
                    int[] visit = { red_y, red_x, blu_y, blu_x, cnt + 1 };
                    toVisiList.add(visit);
                    // System.out.println("visit[0]: " + visit[0] + ", visit[1]: " + visit[1]);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        int[] loc = { 0, 0, 0, 0, 0 }; // (y, x): red, blue, cnt

        for (int i = 0; i < N; i++) {
            String tmp = br.readLine().trim();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'R') {
                    map[i][j] = '.';
                    loc[0] = i;
                    loc[1] = j;
                }
                if (map[i][j] == 'B') {
                    map[i][j] = '.';
                    loc[2] = i;
                    loc[3] = j;
                }
            }
        }

        int ans = bfs(loc);
        System.out.println(ans);
    }
}
// 중력을 이용하여....
// 공의 상대 위치도 고려해서,,,ㅠㅠㅠ
// 두 공의 위치가 걉필 수 밖에 없을 때 == 구멍에 들어갔을 때
/*
 * 틀린 답: 빨간 공 먼저 체크하기
 * 3 5
 * #####
 * #ORB#
 * #####
 * 1 -> 원래 -1
 */
/*
 * 맞는 답
 * 3 10
 * ##########
 * #.O....RB#
 * ##########
 * -1
 */
/*
 * 틀린 답: 주요 원인이었다. 코너케이스,,,확인 또 확인,,,
 * 8 8
 * ########
 * #BR.#.O#
 * ###.#..#
 * #...#..#
 * #.###..#
 * #..#..##
 * ##...#.#
 * ########
 * 11
 */
/*
 * 총평
 * 일반 bfs의 방식으로는 풀 수 없는 문제이다.
 * 조금의 변형이 필요하고 두 구슬 사이의 위치관계, 맵의 형태를 계속 한 틱마다 확인해야한다.
 */