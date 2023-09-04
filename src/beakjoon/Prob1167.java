package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Prob1167 {
    static class Node {
        int num;
        int value;
        int level;
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int V, res, n;
    static ArrayList<Node>[] links;
    static boolean visit[];

    static void dfs(int curr, int dist) {
        if (links[curr].size() == 1 && dist > 0) {
            if (res < dist) {
                res = dist;
                n = curr;
            }
            return;
        }

        for (int i = 0; i < links[curr].size(); i++) {
            Node tmp_node = links[curr].get(i);
            if (!visit[tmp_node.num]) {
                visit[tmp_node.num] = true;
                dfs(tmp_node.num, dist + tmp_node.value);
                visit[tmp_node.num] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        V = Integer.parseInt(br.readLine());
        links = new ArrayList[V + 1];
        visit = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            visit[i] = false;
            links[i] = new ArrayList<>();
        }

        // 인접 리스트 만들기
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 정점 번호
            while (true) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == -1)
                    break;
                Node tmp_node = new Node();
                tmp_node.num = tmp;
                tmp_node.value = Integer.parseInt(st.nextToken());
                links[n].add(tmp_node);
            }
        }

        visit[1] = true;
        dfs(1, 0);
        visit[1] = false;

        visit[n] = true;
        dfs(n, 0);

        System.out.println(res);
    }
}
// 저번에는 LCA를 구해서 직접 풀었지만...
// 다르게 풀어보고 싶어서 이것저것 보다가 트리의 지름 알고리즘이 따로 있다는 것을 알았다.
// 덕분에 코드가 아주 줄었다...
// dfs 두 번이라는 것이 위 알고리즘 이야기였다!!