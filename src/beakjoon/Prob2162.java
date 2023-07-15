package beakjoon;

import java.io.*;
import java.util.*;

// 16:28
class Point {
    double x, y;

    Point(int _x, int _y) {
        this.x = _x;
        this.y = _y;
    }

    boolean isBiggerThan(Point o) {
        if (this.x > o.x)
            return true;
        return false;
    }
}

class Line {
    Point p1, p2;
    double dy, dx;

    Line(Point _p1, Point _p2) {
        if (_p2.isBiggerThan(_p1)) {
            this.p1 = _p1;
            this.p2 = _p2;
        } else {
            this.p1 = _p2;
            this.p2 = _p1;
        }
        this.dy = p2.y - p1.y;
        this.dx = p2.x - p1.x;
    }

    boolean checkMeetRange(Line l) {
        if (!((this.p1.x <= l.p1.x && l.p1.x <= this.p2.x) || (this.p1.x <= l.p2.x && l.p2.x <= this.p2.x)
                || (l.p1.x <= this.p1.x && this.p1.x <= l.p2.x)
                || (l.p1.x <= this.p2.x && this.p2.x <= l.p2.x)))
            return false;

        double[] rangeY = { Math.min(l.p1.y, l.p2.y), Math.max(l.p1.y, l.p2.y), Math.min(this.p1.y, this.p2.y),
                Math.max(this.p1.y, this.p2.y) };

        if (!((rangeY[2] <= l.p1.y && l.p1.y <= rangeY[3]) || (rangeY[2] <= l.p2.y && l.p2.y <= rangeY[3])
                || (rangeY[0] <= this.p1.y && this.p1.y <= rangeY[1])
                || (rangeY[0] <= this.p2.y && this.p2.y <= rangeY[1])))
            return false;
        return true;
    }

    boolean checkMeet(Line l) {
        if (!checkMeetRange(l))
            return false;

        double a = l.dx * this.dy - this.dx * l.dy;
        // 연산 초과 의심!!!!!!!!!!!!
        // double ax = l.dx * this.dy * this.p1.x;
        // ax -= this.dx * l.dy * l.p1.x;
        // ax -= (this.p1.y - l.p1.y) * this.dx * l.dx;

        // 기울기가 비슷할 때
        if (a == 0) {
            // 만나지 않는 단서 찾으면 false
            if (this.dx == 0) {
                if (this.p1.x != l.p1.x)
                    return false;
                return true;
            } else {
                if (this.dy == 0) {
                    if (this.p1.y != l.p1.y)
                        return false;
                    return true;
                }

                double tmp = (l.p1.x - -this.p1.x) * this.dy;
                tmp /= this.dx;
                tmp += this.p1.y - l.p1.y;
                if (tmp != 0)
                    return false;
                return true;
            }

        }
        double l1 = this.dy / this.dx;
        double l2 = l.dy / l.dx;

        double mx = l1 * this.p1.x / (l1 - l2);
        mx -= l2 * l.p1.x / (l1 - l2);
        mx += (l.p1.y - this.p1.y) / (l1 - l2);
        double my = l1 * (mx - this.p1.x) + this.p1.y;

        double[] rangeX = { Math.min(l.p1.x, l.p2.x), Math.max(l.p1.x, l.p2.x),
                Math.min(this.p1.x, this.p2.x),
                Math.max(this.p1.x, this.p2.x) };
        double[] rangeY = { Math.min(l.p1.y, l.p2.y),
                Math.max(l.p1.y, l.p2.y),
                Math.min(this.p1.y, this.p2.y),
                Math.max(this.p1.y, this.p2.y) };

        if ((rangeX[0] <= mx && mx <= rangeX[1] && rangeY[0] <= my && my <= rangeY[1])
                && (rangeX[2] <= mx && mx <= rangeX[3] && rangeY[2] <= my
                        && my <= rangeY[3]))
            return true;
        return false;
    }

}

public class Prob2162 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, group[][];
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
        int grpCnt = 0;
        int maxNodeCnt = 0;
        for (int i = 0; i < N; i++) {
            if (group[i][0] == i) {
                grpCnt++;
                if (maxNodeCnt < group[i][1])
                    maxNodeCnt = group[i][1];
            }
        }
        sb.append(grpCnt).append("\n");
        sb.append(maxNodeCnt).append("\n");
    }

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        init();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 선분 i의 정보
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            Point p1 = new Point(x1, y1);
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            Point p2 = new Point(x2, y2);
            line[i] = new Line(p1, p2);
        }

        for (int l1 = 0; l1 < N; l1++) {
            for (int l2 = l1 + 1; l2 < N; l2++) {
                if (line[l1].checkMeet(line[l2])) {
                    union(l1, l2);
                }
            }
        }

        findRes();
        System.out.println(sb);
    }
}
// A가 -인 경우 고려 못함
// 완전히 일치하는데 범위가 다른 경우를 고려못함
// 방정식으로 풀면 소수점 오차가 생겨서 오류가 발생할 수도 있다고한다.
// 그래서 안 나누자니 오버플로우가 나는것 같다.
// 방정식으로 직접 풀려하니 소수문제, 계산과정에서의 범위문제, 조건부 설정 등 많은 요건이 생겨 계속 오류가 났다.
// 더 쉬운 방법을 찾아봐야겠다. 처음 보는 알고리즘이라고 무시하지 말고 차근차근 풀어보자.
// 지금 이 코드는 내가 60번 트라이 하고 실패한 코드이다.
// CCW 알고리즘으로 풀었을 땐 맞았다고 나왔다.
// 유일하게 방정식으로 푸신 분이 한 분계셨는데 존경의 표시를 보내드린다.
// https://steady-coding.tistory.com/112