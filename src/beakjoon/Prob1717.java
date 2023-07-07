package beakjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1717 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, root[];

    static void init() {
        root = new int[n + 1];
        for (int i = 0; i <= n; i++)
            root[i] = i;
    }

    static void union(int a, int b) {
        // a를 기준
        int a_root = find(a);
        int b_root = find(b);
        root[b_root] = a_root;
    }

    static int find(int num) {
        if (root[num] == num)
            return num;
        root[num] = find(root[root[num]]);
        return root[num];
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            String o = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // equals 주의하기!
            if (o.equals("0")) {
                union(a, b);
            } else {
                if (find(a) == find(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.println(sb.toString());
    }
}
// 유니온 파인드