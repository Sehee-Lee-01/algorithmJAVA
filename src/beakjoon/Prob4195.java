package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 4:57
public class Prob4195 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int T, F, index, root[], cnt_fri[];
    static HashMap<String, Integer> idx_map = new HashMap<>();

    static void init(int F) {
        index = 0;
        idx_map.clear();
        root = new int[2 * F + 1];
        cnt_fri = new int[2 * F + 1];
        for (int i = 0; i <= 2 * F; i++) {
            root[i] = i;
            cnt_fri[i] = 1;
        }
    }

    static int get_idx(String s) {
        if (idx_map.getOrDefault(s, -1) == -1) {
            idx_map.put(s, index);
            return index++;
        }
        return idx_map.get(s);
    }

    static int find(int a) {
        if (root[a] == a) {
            return a;
        }
        int res = find(root[a]);
        root[a] = res;
        return res;
    }

    static int union(int a, int b) {
        int root_a = find(a);
        int root_b = find(b);
        if (root_a == root_b) {
            return cnt_fri[root_a];
        }
        root[root_b] = root_a;
        cnt_fri[root_a] += cnt_fri[root_b];

        return cnt_fri[root_a];
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            F = Integer.parseInt(br.readLine());
            init(F);
            for (int j = 0; j < F; j++) {
                st = new StringTokenizer(br.readLine());
                int f1 = get_idx(st.nextToken());
                int f2 = get_idx(st.nextToken());
                sb.append(union(f1, f2)).append("\n");
            }
        }

        System.out.println(sb.toString());
    }
}
// 이미 같은 그룹일 때 체크