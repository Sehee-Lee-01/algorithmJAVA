package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prob1707 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int K, V, E;
    static ArrayList<Integer>[] node_list;
    static int[] visited;
    static boolean res;

    static int toInt(String s) {
        return Integer.parseInt(s);
    }

    static void init_visit() {
        res = true;
        visited = new int[V + 1];
        node_list = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            visited[i] = 0;
            node_list[i] = new ArrayList<>();
        }
    }

    static void visit(int node, int grp) {
        visited[node] = grp;
        for (int i = 0; i < node_list[node].size(); i++) {
            if (visited[node_list[node].get(i)] == grp) {
                res = false;
                return;
            } else if (visited[node_list[node].get(i)] == grp * (-1))
                continue;
            visit(node_list[node].get(i), grp * (-1));
        }
    }

    public static void main(String[] args) throws IOException {
        K = toInt(br.readLine());
        for (int tc = 0; tc < K; tc++) {
            st = new StringTokenizer(br.readLine());
            V = toInt(st.nextToken());
            E = toInt(st.nextToken());

            init_visit();
            for (int e = 0; e < E; e++) {
                st = new StringTokenizer(br.readLine());
                int u = toInt(st.nextToken());
                int v = toInt(st.nextToken());
                node_list[u].add(v);
                node_list[v].add(u);
            }

            for (int i = 0; i <= V; i++) {
                if (res == false)
                    break;
                if (visited[i] != 0)
                    continue;
                visit(i, 1);
            }

            if (res)
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
// 유니온 파인드 문제로 착각했다
// 리스트를 만들 땐, 리스트의 요소도 초기화 해주자
// 테스트 케이스를 잘 보자