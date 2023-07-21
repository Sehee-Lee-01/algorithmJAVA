package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class EdgeComparator implements Comparator<ArrayList<Integer>> {
    @Override
    public int compare(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.get(0) > b.get(0)) {
            return 1;
        } else if (a.get(0) < b.get(0)) {
            return -1;
        } else {
            if (a.get(1) > b.get(1)) {
                return 1;
            } else if (a.get(1) < b.get(1)) {
                return -1;
            } else {
                if (a.get(2) > b.get(2)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }
    }
}

public class Prob1865 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int TC, N, M, W, S, E, T, dist[];
    static ArrayList<ArrayList<Integer>> edge;

    static void init() {
        edge = new ArrayList<>();
        dist = new int[N + 1];
    }

    static void init_dist(int start) {
        for (int i = 1; i < N + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
    }

    static void clean_edge() {
        // edge.sort(new EdgeComparator());
        // int s = -1;
        // int e = -1;

        // ArrayList<ArrayList<Integer>> temp = new ArrayList<>();
        // for (int i = 0; i < edge.size(); i++) {
        // if (!((s == edge.get(i).get(0)) && (e == edge.get(i).get(1)))) {
        // temp.add(edge.get(i));
        // s = edge.get(i).get(0);
        // e = edge.get(i).get(1);
        // }
        // }
        // edge = temp;
    }

    static void add_edge(int s, int e, int t) {
        ArrayList<Integer> temp = new ArrayList<>();
        temp.add(s);
        temp.add(e);
        temp.add(t);
        edge.add(temp);
    }

    static boolean update_dist(int j) {
        int s = edge.get(j).get(0);
        int e = edge.get(j).get(1);
        int t = edge.get(j).get(2);
        // if (dist[s] != Integer.MAX_VALUE && dist[e] > dist[s] + t) {
        if (dist[e] > dist[s] + t) {
            dist[e] = dist[s] + t;
            return true;
        }
        return false;

    }

    static void min_dist() {
        // N - 1번 반복
        for (int update = 0; update < N - 1; update++) {
            for (int j = 0; j < edge.size(); j++) {
                int s = edge.get(j).get(0);
                if (dist[s] == Integer.MAX_VALUE)
                    continue;
                update_dist(j);
            }
        }
    }

    static boolean seek_cycle() {
        for (int j = 0; j < edge.size(); j++)
            if (update_dist(j))
                return true;
        return false;
    }

    public static void main(String[] args) throws IOException {
        TC = Integer.parseInt(br.readLine());
        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 지점의 개수
            M = Integer.parseInt(st.nextToken()); // 도로의 개수
            W = Integer.parseInt(st.nextToken()); // 웜홀의 개수
            init();

            // 도로는 방향이 없으며 웜홀은 방향이 있다.
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                // 연결된 지점의 번호
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                // 도로를 통해 이동하는데 걸리는 시간
                T = Integer.parseInt(st.nextToken());
                add_edge(S, E, T);
                add_edge(E, S, T);
            }
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                // 연결된 지점의 번호
                S = Integer.parseInt(st.nextToken());
                E = Integer.parseInt(st.nextToken());
                // 웜홀을 통해 이동하는데 걸리는 시간
                T = Integer.parseInt(st.nextToken()) * (-1);
                add_edge(S, E, T);
            }

            clean_edge();

            boolean cycle = false;
            // for (int start = 1; start < N + 1; start++) {
            // init_dist(start);
            min_dist();
            cycle = seek_cycle();
            if (cycle) {
                sb.append("YES\n");
                // break;
            }
            // }
            // if (!cycle) {
            else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb.toString());
    }
}
// 음수 사이클이 있는지 없는지만 확인하면 되는 문제
// 최단거리까지 정식으로 구하려면 시간초과가 난다.
// 문제의 목적을 잘 살펴보자
// 참고로 음수사이클을 판변하는 것이기 때문에 시작점을 정해주지 않아도 괜찮다.
// 노드 중복을 없애는 것도 중요하겠디만 무엇보다도 각 노드마다 출발점 하는 것이 더 연산이 많을 수도 있다.
// O((E-a)*N)보다는 O(E)가 낫지 않겠는가...
// (5200-a)*500*500 = 1300000000 vs 5200*500 = 2600000
// 참고: https://www.acmicpc.net/board/view/72995
// 로직은 완벽해보였지만 문제에서 요구하는 것보다 더 많은 것(과한 것)을 구현하고 있었다.