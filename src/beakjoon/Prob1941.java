// 15:20
package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Prob1941 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static final char[][] MAP = new char[5][5];
    static final boolean[] DASOM = new boolean[25];
    static final int P_COUNT = 7;
    static final int MAX_Y_COUNT = 3;
    static int res = 0;

    static void seekAdj(int[] arr) {
        boolean[] princess = new boolean[25];
        for (int i = 0; i < 25; i++) {
            princess[i] = false;
        }
        for (int i = 0; i < P_COUNT; i++) {
            princess[arr[i]] = true;
        }

        // bfs
        int adjCnt = 0;
        int[] dir = { -5, 5, -1, 1 };

        Queue<Integer> q = new LinkedList<>();
        q.add(arr[0]);
        princess[arr[0]] = false;
        adjCnt++;

        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i = 0; i < 4; i++) {
                if (curr + dir[i] >= 0 && curr + dir[i] < 25) {
                    if (1 < i && (curr + dir[i]) / 5 != curr / 5)
                        continue;
                    if (princess[curr + dir[i]]) {
                        q.add(curr + dir[i]);
                        princess[curr + dir[i]] = false;
                        adjCnt++;
                    }
                }
            }
        }

        if (adjCnt == P_COUNT)
            res++;
    }

    static void seekSeven(int[] arr, int curr, int len, int yCnt) {
        if (MAX_Y_COUNT < yCnt || curr > 24) { // 마지막 주의
            if (curr == 25 && len == 7 && MAX_Y_COUNT >= yCnt)
                seekAdj(arr);
            return;
        }
        if (len == P_COUNT) {
            seekAdj(arr);
            return;
        }
        seekSeven(arr, curr + 1, len, yCnt);
        arr[len] = curr;
        seekSeven(arr, curr + 1, len + 1,
                DASOM[curr] ? yCnt : yCnt + 1);
    }

    public static void main(String[] args) throws IOException {
        // init
        for (int r = 0; r < 5; r++) {
            String rStr = br.readLine();
            for (int c = 0; c < 5; c++) {
                MAP[r][c] = rStr.charAt(c);
            }
        }
        for (int i = 0; i < 25; i++) {
            DASOM[i] = (MAP[i / 5][i % 5] == 'S');
        }

        // run =>조합을 만들고 BFS로 확인하자.
        int[] arr = new int[P_COUNT];
        seekSeven(arr, 0, 0, 0);

        // print
        System.out.println(res);
    }
}
