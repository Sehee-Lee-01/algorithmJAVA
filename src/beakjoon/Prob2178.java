package beakjoon;

import java.io.*;
import java.util.*;

public class Prob2178 {
    static void bfs(int[][] map, int N, int M) {
        ArrayList<int[]> queue = new ArrayList<>();
        // 이전 값은 사실 저장할 필요 없다.(이전 값으로 순회할 경우에는 무한 루프 발생 가능)
        // cnt 수대로 map에 각 칸에 접근한 최소 거리를 저장하자.
        int[] start = { 0, 0, 1 }; 
        queue.add(start);
        while (!queue.isEmpty()) {
            int[] front = queue.remove(0);
            // System.out.println(front[0]);
            int y = front[0];
            int x = front[1];
            int cnt = front[2];

            if ((map[y][x] != 1) && (map[y][x] <= cnt) && !(y == 0 && x == 0)) continue; 

            map[y][x] = cnt;

            if (y == N - 1 && x == M - 1) 
                break;
            
                // 오른
            if (x < M - 1 && map[y][x + 1] > 0) {
                int[] temp = { y, x + 1, cnt + 1 };
                queue.add(temp);
            }
            // 아래
            if (y < N - 1 && map[y + 1][x] > 0) {
                int[] temp = { y + 1, x, cnt + 1 };
                queue.add(temp);
            }
            // 왼
            if (x > 0 && map[y][x - 1] > 0) {
                int[] temp = { y, x - 1, cnt + 1 };
                queue.add(temp);
            }
            // 위
            if (y > 0 && map[y - 1][x] > 0) {
                int[] temp = { y - 1, x, cnt + 1 };
                queue.add(temp);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        bfs(map, N, M);
        System.out.println(map[N - 1][M - 1]);
    }
}
