package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 17:48~ 21:46
public class Prob1967 {
    static class Node {
        int parent = -1;
        int value = -1;
        int level = -1;

        ArrayList<Integer> child = new ArrayList<>();
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, res = 0;
    static Node[] nodes;

    static void set_level(int curr) {
        if (nodes[curr].child.isEmpty())
            return;

        for (int i = 0; i < nodes[curr].child.size(); i++) {
            int child = nodes[curr].child.get(i);
            nodes[child].level = nodes[curr].level + 1;
            set_level(child);
        }
    }

    static void seek_max(int i, int j) {
        // 길찾기
        int curr = i;
        int curr2 = j;
        int sum = 0;

        if (nodes[curr].level < nodes[curr2].level) {
            while (nodes[curr].level != nodes[curr2].level) {
                sum += nodes[curr2].value;
                curr2 = nodes[curr2].parent;
            }
        } else if (nodes[curr].level > nodes[curr2].level) {
            while (nodes[curr].level != nodes[curr2].level) {
                sum += nodes[curr].value;
                curr = nodes[curr].parent;
            }
        }

        while (curr != curr2) {
            sum += nodes[curr].value;
            sum += nodes[curr2].value;

            curr = nodes[curr].parent;
            curr2 = nodes[curr2].parent;
        }

        if (res < sum)
            res = sum;

    }

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(br.readLine());
        nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node();
            nodes[i].parent = -1;
            nodes[i].value = -1;
            nodes[i].level = -1;
        }

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            nodes[p].child.add(c);
            nodes[c].parent = p;
            nodes[c].value = v;
        }

        nodes[0].level = 1;
        set_level(0);

        ArrayList<Integer> leafs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nodes[i].child.isEmpty() || (nodes[i].parent == -1 && nodes[i].child.size() == 1)) {
                leafs.add(i);
            }
        }

        for (int i = 0; i < leafs.size() - 1; i++) {
            for (int j = i + 1; j < leafs.size(); j++) {
                seek_max(leafs.get(i), leafs.get(j));
            }
        }

        System.out.println(res);
    }
}
// 중복 연산이 있어서 시간 초과가 떴다.(LCA 찾기)
// 자식 노드에서 부모 노드로 가는 길은 하나로 쭉 가기 때문에 그 특성을 이용했다.
// 부모랑 자식 노드 저장할 때 메모리 헷갈리지 말기!!!
// 그래프를 어떤식으로 만들지 생각하는 연습이 필요할 것 같다.