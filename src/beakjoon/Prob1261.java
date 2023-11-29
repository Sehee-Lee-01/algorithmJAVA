package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// 10:25
// bfs로 풀었습니당!
public class Prob1261 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, defaultWallCnt;
    static int[] dirY = { -1, 1, 0, 0 }, dirX = { 0, 0, -1, 1 };
    static int[][] map, mapWallCnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        mapWallCnt = new int[N][M];
        defaultWallCnt = N * M + 1;

        for (int i = 0; i < N; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                map[i][j] = row[j] - '0';
                mapWallCnt[i][j] = defaultWallCnt;
            }
        }

        mapWallCnt[0][0] = 0;
        Deque<int[]> stack = new LinkedList<>();
        stack.push(new int[] { 0, 0, 0 });
        while (!stack.isEmpty()) {
            int[] curr = stack.pollLast();
            int y = curr[0];
            int x = curr[1];
            int wallCnt = curr[2];

            if (y == N - 1 && x == M - 1) {
                continue;
            }
            for (int i = 0; i < 4; i++) {
                int nexY = y + dirY[i];
                int nexX = x + dirX[i];
                if (nexY >= 0 && nexY < N && nexX >= 0 && nexX < M) {
                    int nexWallCnt = (map[nexY][nexX] == 1) ? wallCnt + 1 : wallCnt;
                    if (mapWallCnt[nexY][nexX] > nexWallCnt) {
                        mapWallCnt[nexY][nexX] = nexWallCnt;
                        stack.push(new int[] { nexY, nexX, nexWallCnt });
                    }
                }
            }
        }
        System.out.println(mapWallCnt[N - 1][M - 1]);

    }
}
