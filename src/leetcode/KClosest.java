package leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    class Point {
        int[] coordinate;
        int dis;

        public Point(int[] coordinate) {
            this.coordinate = coordinate;
            this.dis = Math.abs(coordinate[0] * coordinate[0] + coordinate[1] * coordinate[1]);
        }
    }

    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> pq = new PriorityQueue<>((a, b) -> a.dis - b.dis);
        for (int[] p : points) {
            Point point = new Point(p);
            pq.add(point);
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = pq.poll().coordinate;
        }

        return res;
    }
}