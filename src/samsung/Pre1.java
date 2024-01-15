package samsung;

import java.util.*;
import java.io.*;

class Solution1 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static final int MAX_LEN = 10001;
    static int T;
    static int[] triangle;
    static List<Integer> levelArrange;

    public static void main(String args[]) throws IOException {
        T = Integer.parseInt(br.readLine());
        init();

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (s > e) {
                int tmp = s;
                s = e;
                e = tmp;
            }

            sb.append("#")
                    .append(test_case)
                    .append(" ")
                    .append(seekMinDis(s, e))
                    .append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int seekMinDis(int s, int e) {
        if (s == e) {
            return 0;
        }

        triangle = new int[MAX_LEN];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (curr == e) {
                break;
            }

            int currLevel = seekLevel(curr);
            int currPreLevelMax = levelArrange.get(currLevel - 1);
            int currLevelMax = levelArrange.get(currLevel);

            // left
            int left = curr - 1;
            if ((0 < left) && (currLevel == seekLevel(left))
                    && (triangle[left] == 0 || triangle[curr] + 1 < triangle[left])) {
                triangle[left] = triangle[curr] + 1;
                q.add(left);
            }

            // right
            int right = curr + 1;
            if ((right < MAX_LEN) && (currLevel == seekLevel(right))
                    && (triangle[right] == 0 || triangle[curr] + 1 < triangle[right])) {
                triangle[right] = triangle[curr] + 1;
                q.add(right);
            }

            // down_left
            int downLeft = currLevelMax + (curr - currPreLevelMax);
            if ((downLeft < MAX_LEN)
                    && (triangle[downLeft] == 0 || triangle[curr] + 1 < triangle[downLeft])) {
                triangle[downLeft] = triangle[curr] + 1;
                q.add(downLeft);
            }

            // down_right
            int downRight = currLevelMax + (curr - currPreLevelMax) + 1;
            if ((downRight < MAX_LEN)
                    && (triangle[downRight] == 0 || triangle[curr] + 1 < triangle[downRight])) {
                triangle[downRight] = triangle[curr] + 1;
                q.add(downRight);
            }
        }

        return triangle[e];
    }

    private static void init() {
        levelArrange = new ArrayList<>();
        levelArrange.add(0);

        int level = 1;
        int sum = level;
        while (sum < MAX_LEN) {
            levelArrange.add(sum);
            level++;
            sum += level;
        }
        levelArrange.add(MAX_LEN - 1);
    }

    private static int seekLevel(int n) {
        for (int level = levelArrange.size() - 1; level >= 1; level--) {
            if (levelArrange.get(level) < n) {
                return level + 1;
            }
        }
        return 1;
    }
}