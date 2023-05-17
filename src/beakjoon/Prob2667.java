package beakjoon;

import java.io.*;
import java.util.*;

public class Prob2667 {
    static void seekGroup(int N, Integer[][] map, Integer[][] group, int i, int j, ArrayList<Integer> groupCnt) {
        Integer[] start = { i, j };
        ArrayList<Integer[]> queue = new ArrayList<>();
        queue.add(start);
        groupCnt.add(0);
        int groupNum = groupCnt.size();
        while (!queue.isEmpty()) {
            Integer[] cur = queue.remove(0);
            int y = cur[0];
            int x = cur[1];
            if (group[y][x] == 0) { // 그룹 표시 및 그룹 원소 개수 갱신
                group[y][x] = groupNum;
                groupCnt.set(groupNum - 1, groupCnt.get(groupNum - 1) + 1);
            } else
                continue;

            // 상하좌우 탐색
            // 상
            if (y > 0 && map[y - 1][x] == 1 && group[y - 1][x] == 0)
                queue.add(new Integer[] { y - 1, x });
            // 하
            if (y < N - 1 && map[y + 1][x] == 1 && group[y + 1][x] == 0)
                queue.add(new Integer[] { y + 1, x });
            // 좌
            if (x > 0 && map[y][x - 1] == 1 && group[y][x - 1] == 0)
                queue.add(new Integer[] { y, x - 1 });
            // 우
            if (x < N - 1 && map[y][x + 1] == 1 && group[y][x + 1] == 0)
                queue.add(new Integer[] { y, x + 1 });
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Integer[][] map = new Integer[N][N];
        Integer[][] group = new Integer[N][N];
        ArrayList<Integer> groupCnt = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = Character.getNumericValue(str.charAt(j));
                group[i][j] = 0;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1 && group[i][j] == 0)
                    seekGroup(N, map, group, i, j, groupCnt);
            }
        }
        StringBuffer sb = new StringBuffer();
        sb.append(groupCnt.size()).append("\n");
        Collections.sort(groupCnt);
        for (int cnt : groupCnt)
            sb.append(cnt).append("\n");
        System.out.println(sb);
    }
}
