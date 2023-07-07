package beakjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Prob2252 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M, connect[];
    static ArrayList<Integer>[] node_list;
    static Queue<Integer> zero_connect = new LinkedList<>();

    static void init() {
        node_list = new ArrayList[N + 1];
        connect = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            connect[i] = 0;
            node_list[i] = new ArrayList<>();
        }
    }

    static void topo() {
        for (int i = 1; i <= N; i++) {
            if (connect[i] == 0)
                zero_connect.add(i);
        }

        while (!zero_connect.isEmpty()) {
            int curr = zero_connect.poll();
            connect[curr] = -1;
            sb.append(curr).append(" ");
            for (int i = 0; i < node_list[curr].size(); i++) {
                int nex = node_list[curr].get(i);
                connect[nex]--;
                if (connect[nex] == 0)
                    zero_connect.add(nex);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        init();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            node_list[A].add(B);
            connect[B]++;
        }

        topo();

        System.out.println(sb.toString());
    }
}
// 위상 정렬
// N+1, N 조심 