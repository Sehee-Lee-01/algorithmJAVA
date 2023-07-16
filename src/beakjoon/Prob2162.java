package beakjoon;

import java.io.*;
import java.util.*;

// 16:28
class Point {
    double x, y;

    Point(double _x, double _y) {
        this.x = _x;
        this.y = _y;
    }

}

class Line {
    Point p1, p2;
    double dy, dx, OCHA = 0.0001;

    Line(Point _p1, Point _p2) {
        this.p1 = _p1;
        this.p2 = _p2;
        this.dy = p2.y - p1.y;
        this.dx = p2.x - p1.x;
    }

    boolean checkMeet(Line l) {
        double[] rangeX = { Math.min(l.p1.x, l.p2.x) - OCHA,
                Math.max(l.p1.x, l.p2.x) + OCHA,
                Math.min(this.p1.x, this.p2.x) - OCHA,
                Math.max(this.p1.x, this.p2.x) + OCHA };
        double[] rangeY = { Math.min(l.p1.y, l.p2.y) - OCHA,
                Math.max(l.p1.y, l.p2.y) + OCHA,
                Math.min(this.p1.y, this.p2.y) - OCHA,
                Math.max(this.p1.y, this.p2.y) + OCHA };

        if (this.dx == 0 && l.dx == 0) {
            // 둘 다 y축 평행 일때
            if ((this.p1.x == l.p1.x) && (rangeY[0] <= rangeY[2] && rangeY[2] <= rangeY[1]
                    || rangeY[0] <= rangeY[3] && rangeY[3] <= rangeY[1]
                    || rangeY[2] <= rangeY[0] && rangeY[0] <= rangeY[3]
                    || rangeY[2] <= rangeY[1] && rangeY[1] <= rangeY[3]))
                return true;
            return false;
        }
        // x = a
        // m(a-x1) +y1 = y
        if (this.dx == 0) {
            double m, mx, my;
            // 자신만 평행 직선일 때
            mx = this.p1.x;
            if (!(rangeX[0] <= mx && mx <= rangeX[1]))
                return false;
            m = l.dy / l.dx;
            my = m * (mx - l.p1.x) + l.p1.y;
            if ((rangeY[0] <= my && my <= rangeY[1]) && (rangeY[2] <= my
                    && my <= rangeY[3]))
                return true;
            return false;
        }
        if (l.dx == 0) {
            double m, mx, my;
            // l만 y축 평행 직선 일 때
            mx = l.p1.x;
            if (!(rangeX[2] <= mx && mx <= rangeX[3]))
                return false;
            m = this.dy / this.dx;
            my = m * (mx - this.p1.x) + this.p1.y;
            if ((rangeY[0] <= my && my <= rangeY[1]) && (rangeY[2] <= my
                    && my <= rangeY[3]))
                return true;
            return false;
        }

        // 둘 다 x축 평행이 아닐 때
        // m(x-x1)+y1 = y;
        // mx-mx1 + y1 = y;
        // A = y1 - mx1;
        // mx + A = y;
        double m1 = this.dy / this.dx;
        double A1 = this.p1.y - m1 * this.p1.x;

        double m2 = l.dy / l.dx;
        double A2 = l.p1.y - m2 * l.p1.x;
        if (m1 == m2) {
            // 기울기가 같을 떄
            if ((A1 == A2) && ((rangeX[0] <= rangeX[2] && rangeX[2] <= rangeX[1])
                    || (rangeX[0] <= rangeX[3] && rangeX[3] <= rangeX[1])
                    || (rangeX[2] <= rangeX[0] && rangeX[0] <= rangeX[3])
                    || (rangeX[2] <= rangeX[1] && rangeX[1] <= rangeX[3])) &&
                    ((rangeY[0] <= rangeY[2] && rangeY[2] <= rangeY[1])
                            || (rangeY[0] <= rangeY[3] && rangeY[3] <= rangeY[1])
                            || (rangeY[2] <= rangeY[0] && rangeY[0] <= rangeY[3])
                            || (rangeY[2] <= rangeY[1] && rangeY[1] <= rangeY[3]))) {
                // 일치하면(x,y 둘 다 겹치는지 확인)
                return true;
            }
            // 평행이면
            return false;
        }
        // 한 점에서 만날 때 한 점이 두 범위에 속하는지 검사
        // x = - (A1-A2)/ (m1-m2) = X;
        // m1*x + A1 = y; -> m1*X + A1 = y;
        double mx = (A2 - A1) / (m1 - m2);
        double my = m1 * mx + A1;
        if ((rangeX[0] <= mx && mx <= rangeX[1] && rangeX[2] <= mx && mx <= rangeX[3])
                && (rangeY[0] <= my && my <= rangeY[1] && rangeY[2] <= my
                        && my <= rangeY[3]))
            return true;
        return false;
    }

}

public class Prob2162 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, group[][], grpCnt = 0, maxNodeCnt = 0, lineCnt = 0;;
    static Line line[];

    static void init() {
        line = new Line[N];
        group = new int[N][2];
        for (int i = 0; i < N; i++) {
            group[i][0] = i; // root
            group[i][1] = 1; // nodeCnt
        }
    }

    static int find(int l) {
        if (group[l][0] == l)
            return l;

        int curr = group[l][0];
        while (curr != group[curr][0]) {
            curr = group[curr][0];
        }
        group[l][0] = curr;
        return curr;
    }

    static void union(int l1, int l2) {
        int root1 = find(l1);
        int root2 = find(l2);
        if (root1 == root2)
            return;

        group[root2][0] = root1;
        group[root1][1] += group[root2][1];
    }

    static void findRes() {
        for (int i = 0; i < lineCnt; i++) {
            if (group[i][0] == i) {
                grpCnt++;
                if (maxNodeCnt < group[i][1])
                    maxNodeCnt = group[i][1];
            }
        }
    }

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        init();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 선분 i의 정보
            double x1 = Double.parseDouble(st.nextToken());
            double y1 = Double.parseDouble(st.nextToken());
            Point p1 = new Point(x1, y1);
            double x2 = Double.parseDouble(st.nextToken());
            double y2 = Double.parseDouble(st.nextToken());
            Point p2 = new Point(x2, y2);
            if (p1.x == p2.x && p1.y == p2.y)
                continue;
            line[lineCnt++] = new Line(p1, p2);
        }

        for (int l1 = 0; l1 < lineCnt; l1++) {
            for (int l2 = l1 + 1; l2 < lineCnt; l2++) {
                if (line[l1].checkMeet(line[l2])) {
                    union(l1, l2);
                }
            }
        }

        findRes();
        System.out.println(grpCnt);
        System.out.println(maxNodeCnt);
    }
}
