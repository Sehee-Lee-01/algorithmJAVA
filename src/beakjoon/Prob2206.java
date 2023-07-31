package beakjoon;

// 4:41
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob2206 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, map[][], dir_y[] = { -1, 1, 0, 0 }, dir_x[] = { 0, 0, -1, 1 };
    static int visit0[][], visit1[][];

    static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int[] start = { 1, 1, 0 }; // y, x, chance
        visit0[1][1] = 1;
        q.add(start);
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int y = curr[0], x = curr[1], chance = curr[2];
            int dis = (chance == 0) ? visit0[y][x] : visit1[y][x];
            if (y == N && x == M) {
                return dis;
            }

            for (int i = 0; i < 4; i++) {
                int nex_y = y + dir_y[i], nex_x = x + dir_x[i];
                if (nex_y > 0 && nex_y <= N && nex_x > 0 && nex_x <= M) {
                    int nex[] = { nex_y, nex_x, chance };
                    if (nex[2] == 0) {
                        // 벽 부수지 않은 상태
                        if (map[nex_y][nex_x] == 0 && visit0[nex_y][nex_x] == -1) {
                            // 블럭이 0일 때
                            visit0[nex_y][nex_x] = dis + 1;
                            q.add(nex);
                        } else if (map[nex_y][nex_x] == 1 && visit1[nex_y][nex_x] == -1) {
                            // 블럭이 0일 때
                            nex[2] = 1; // 찬스 표시
                            visit1[nex_y][nex_x] = dis + 1;
                            q.add(nex);
                        }
                    } else if (map[nex_y][nex_x] == 0 && visit1[nex_y][nex_x] == -1) {
                        // 벽 부순 상태
                        visit1[nex_y][nex_x] = dis + 1;
                        q.add(nex);
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];
        visit0 = new int[N + 1][M + 1];
        visit1 = new int[N + 1][M + 1];
        String temp;
        for (int i = 1; i <= N; i++) {
            temp = br.readLine().trim();
            for (int j = 1; j <= M; j++) {
                map[i][j] = temp.charAt(j - 1) - '0';
                visit0[i][j] = -1;
                visit1[i][j] = -1;
            }
        }

        System.out.println(bfs());
    }
}
// 좌표 시작과 끝 주의
// visit를 구분해야한다. 1을 한번 넘어가 도착한 칸과 0만 넘어서 도착한 칸이 겹친다.
// 1을 한번 넘어가 도착한 칸: visit에 먼저 표시된다.
// 0만 넘어서 도착한 칸: 이미 visit 표시가 되어 있을 수도 있어, 되어있다면 측정 불가.
// 한 칸 한 칸 마다 1을 넘어서 갔는지 0만 넘어서 갔는지 거리 구분해서 확인 필요!!!
// 시도1: 1을 아직 안가고 그 칸까지 간 거리와 1을 지나치고 그 칸 까지 간 거리를 따로 저장
// 조건문의 위치를 바꾸니 성공 -> 조건문의 순서를 단순화 하자.
// 처음에는 다음 좌표가 0인지 1인지에 따라 알고리즘을 나눔
// 두번 째로는 벽을 부순 상태인지 아닌지 조건에 따라 나눔
// 조건 빠져나가는 부분 잘 보기