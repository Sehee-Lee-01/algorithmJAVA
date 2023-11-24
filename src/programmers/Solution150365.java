package programmers;

// 1:00 ~ 4:10
class Solution150365 {
    static int N, M, R, C, K_LEN;
    static char[] dirC = { 'd', 'l', 'r', 'u' }, K;
    static int[] dirY = { 1, 0, 0, -1 }, dirX = { 0, -1, 1, 0 };
    static StringBuilder answer = new StringBuilder();

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        R = r;
        C = c;
        K = new char[k];
        K_LEN = k;
        for (int i = 0; i < k; i++) {
            K[i] = '0';
        }

        return dfs(x, y, 0) ? answer.toString() : "impossible";
    }

    boolean dfs(int y, int x, int dis) {
        if ((Math.abs(y - R) + Math.abs(x - C)) > (K_LEN - dis) ||
                ((K_LEN - dis) - (Math.abs(y - R) + Math.abs(x - C))) % 2 == 1) // 홀수면 돌아갈 수 없다 이런,,,
            // 나는 위의 코드에서 딱 남은 이동 거리 = 1 + 최단 거리 경우만 생각했다.
            return false;

        if (0 == K_LEN - dis) {
            if (y == R && x == C) {
                for (char c : K) {
                    answer.append(c);
                }
                return true;
            }
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nexY = y + dirY[i];
            int nexX = x + dirX[i];
            if (nexY <= N && nexX >= 1 && nexX <= M && nexY >= 1) {
                K[dis] = dirC[i];
                if (dfs(nexY, nexX, dis + 1))
                    return true;
            }
        }
        return false;
    }
}