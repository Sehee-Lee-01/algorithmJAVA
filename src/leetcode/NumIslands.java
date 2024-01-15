package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    static int[] dirY = { -1, 1, 0, 0 }, dirX = { 0, 0, -1, 1 };
    static char[][] map;
    static boolean[][] visited;
    static int m, n;

    public int numIslands(char[][] grid) {
        map = grid;
        n = map.length;
        m = map[0].length;
        visited = new boolean[n][m];

        int totalCnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isNotVisitedAndIsIsland(i, j)) {
                    bfs(i, j);
                    totalCnt++;
                }
            }
        }

        return totalCnt;
    }

    private void bfs(int i, int j) {
        Deque<int[]> q = new ArrayDeque();
        visited[i][j] = true;
        q.add(new int[] { i, j });
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int k = 0; k < 4; k++) {
                int nexY = curr[0] + dirY[k];
                int nexX = curr[1] + dirX[k];
                if (isInBoundary(nexY, n) && isInBoundary(nexX, m) &&
                        isNotVisitedAndIsIsland(nexY, nexX)) {
                    visited[nexY][nexX] = true;
                    q.add(new int[] { nexY, nexX });
                }
            }
        }

    }

    private boolean isNotVisitedAndIsIsland(int i, int j) {
        return map[i][j] == '1' && !visited[i][j];
    }

    private boolean isInBoundary(int cor, int max) {
        return cor >= 0 && cor < max;
    }
}