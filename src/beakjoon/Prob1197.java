package beakjoon;

//10:01
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Edge {
    public final int v1;
    public final int v2;
    public final Long w;

    public Edge(int v1, int v2, Long w) {
        this.v1 = v1;
        this.v2 = v2;
        this.w = w;
    }
}

public class Prob1197 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int V, E, parents[];
    static ArrayList<Edge> elist;

    static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parents = new int[V + 1];
        elist = new ArrayList<>();

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            Long w = Long.parseLong(st.nextToken());

            if (v1 == v2)
                continue;

            Edge edge = new Edge(v1, v2, w);
            elist.add(edge);
        }

        elist.sort((edge1, edge2) -> edge1.w <= edge2.w ? -1 : 1);
    }

    static int find(int v) {
        if (parents[v] == v)
            return v;
        parents[v] = find(parents[v]);
        return parents[v];
    }

    /*
     * wrong code: 루트!!!!를 설정해주지 않았다!!!!
     * static void union(int v1, int v2) {
     * if (parents[v1] < parents[v2]) {
     * parents[v2] = parents[v1];
     * } else {
     * parents[v1] = parents[v2];
     * }
     * }
     */

    static void union(int v1, int v2) {
        int a = find(parents[v1]);
        int b = find(parents[v2]);
        if (a < b) {
            parents[a] = b;
        } else {
            parents[b] = a;
        }
    }

    public static void main(String[] args) throws IOException {
        init();
        if (V == 1) {
            System.out.println(0);
            return;
        }
        long sum = 0;
        long cnt = 0;
        for (int i = 0; i < E; i++) {
            int v1 = elist.get(i).v1;
            int v2 = elist.get(i).v2;
            Long w = elist.get(i).w;

            if (find(v1) == find(v2))
                continue;
            union(v1, v2);

            sum += w;
            if (++cnt == V - 1)
                break;
        }
        System.out.println(sum);
    }
}
