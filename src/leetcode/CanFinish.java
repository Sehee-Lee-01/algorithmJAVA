package leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

// 12:28
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            return true;

        // init
        int[] root = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            root[i] = i;
        }

        int[] mustCnt = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            root[prerequisite[0]] = prerequisite[1];
            mustCnt[prerequisite[1]]++;
        }

        int start = seekStart(root, mustCnt);
        if (start == -1)
            return false;

        boolean[] visited = new boolean[numCourses];
        Queue<Integer> q = new ArrayDeque<>();
        visited[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i = 0; i < numCourses; i++) {
                if (root[i] == curr && !visited[i]) {
                    visited[i] = true;
                    q.add(i);
                }
            }
        }

        int visitedCnt = 0;
        for (int i = 0; i < numCourses; i++) {
            if (visited[i]) {
                visitedCnt++;
            }
        }

        return visitedCnt == numCourses ? true : false;

    }

    private int seekStart(int[] root, int[] mustCnt) {
        for (int i = 0; i < root.length; i++) {
            if (root[i] == i && mustCnt[i] > 0)
                return i;
        }
        return -1;
    }
}