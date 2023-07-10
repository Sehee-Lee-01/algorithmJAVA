package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob14940 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, y, x;
    static int[][] map, dis; // 0: 갈 수 없다, 1: 갈 수 있다, 2: 목표지점(1개)

    static void print_map() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (dis[i][j] == 0 && map[i][j] == 1)
                    sb.append("-1");
                else
                    sb.append(dis[i][j]);
                sb.append(" ");
            }
            sb.append("\n");
        }
        String res = sb.toString();
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dis = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                dis[i][j] = 0;
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    y = i;
                    x = j;
                }
            }
        }

        Queue<int[]> toVisit = new LinkedList<>();
        toVisit.add(new int[] { y, x });
        int dis_y[] = { 1, -1, 0, 0 }, dis_x[] = { 0, 0, -1, 1 };
        while (!toVisit.isEmpty()) {
            int curr[] = toVisit.remove();
            int cur_y = curr[0];
            int cur_x = curr[1];
            for (int i = 0; i < 4; i++) {
                int nex_y = cur_y + dis_y[i], nex_x = cur_x + dis_x[i];
                if (nex_y >= 0 && nex_y < n && nex_x >= 0 && nex_x < m && map[nex_y][nex_x] == 1
                        && dis[nex_y][nex_x] == 0) {
                    dis[nex_y][nex_x] = dis[cur_y][cur_x] + 1;
                    toVisit.add(new int[] { nex_y, nex_x });
                }
            }
            // print_map();
        }
        print_map();
    }
}
// System out을 많이 하는 것보다는 StringBuilder를 쓰자.