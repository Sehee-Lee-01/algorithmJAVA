package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

// 15:338
public class Prob1238 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, X, dist1[], dist2[];
    static ArrayList<Destination>[] m1, m2;

    static class Destination {
        int end_num;
        int time;

        Destination(int n, int t) {
            this.end_num = n;
            this.time = t;
        }
    }

    public static void main(String args[]) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;

        dist1 = new int[N];
        m1 = new ArrayList[N];
        dist2 = new int[N];
        m2 = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            dist1[i] = -1;
            m1[i] = new ArrayList<>();
            dist2[i] = -1;
            m2[i] = new ArrayList<>();
        }
        dist1[X] = 0;
        dist2[X] = 0;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int T = Integer.parseInt(st.nextToken());

            m1[s].add(new Destination(e, T));
            m2[e].add(new Destination(s, T));
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(X);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i = 0; i < m1[curr].size(); i++) {
                Destination d = m1[curr].get(i);
                if (dist1[d.end_num] == -1 || dist1[d.end_num] > dist1[curr] + d.time) {
                    dist1[d.end_num] = dist1[curr] + d.time;
                    q.add(d.end_num);
                }
            }
        }

        q.add(X);
        while (!q.isEmpty()) {
            int curr = q.poll();
            for (int i = 0; i < m2[curr].size(); i++) {
                Destination d = m2[curr].get(i);
                if (dist2[d.end_num] == -1 || dist2[d.end_num] > dist2[curr] + d.time) {
                    dist2[d.end_num] = dist2[curr] + d.time;
                    q.add(d.end_num);
                }
            }
        }

        int res = 0;
        for (int i = 0; i < N; i++) {
            if (res < dist1[i] + dist2[i])
                res = dist1[i] + dist2[i];
        }

        System.out.println(res);
    }
}
// 단방향이지만 리버스하면 가는 길도 구할 수 있다.
// 방향을 뒤집는 것도 생각해보자!!!
// 그리고 마을이 1부터 N까지이다 보니 인덱스 오류가 있었다...