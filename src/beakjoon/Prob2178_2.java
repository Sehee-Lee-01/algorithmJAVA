package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//12:29 ~ 12:53
public class Prob2178_2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static char[][] map;
    static int[][] distance;
    static int[] y_dir = { -1, 1, 0, 0 }, x_dir = { 0, 0, -1, 1 };

    static class Location {
        int r;
        int c;
        int dis;

        public Location(int r, int c) {
            this(r, c, 0);
        }

        public Location(int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            map[i] = row.toCharArray();

            for (int j = 0; j < M; j++) {
                distance[i][j] = map[i][j] == '1' ? 0 : -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        Queue<Location> q = new LinkedList<>();
        q.add(new Location(0, 0, 1));
        distance[0][0] = 1;

        while (!q.isEmpty()) {
            Location curr = q.poll();
            if (curr.r == N && curr.c == M)
                break;

            for (int i = 0; i < 4; i++) {
                int nex_r = curr.r + y_dir[i];
                int nex_c = curr.c + x_dir[i];
                if ((nex_r > -1 && nex_r < N) && (nex_c > -1 && nex_c < M)) {
                    if (map[nex_r][nex_c] == '1' && distance[nex_r][nex_c] == 0) {
                        distance[nex_r][nex_c] = curr.dis + 1;
                        q.add(new Location(nex_r, nex_c, curr.dis + 1));
                    }
                }
            }
        }

        System.out.println(distance[N - 1][M - 1]);
    }
}
