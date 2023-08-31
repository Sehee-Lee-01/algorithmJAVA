package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob11404 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int n, m, res[][];
    static final int MAX = 1000001;

    static void init_res() throws IOException {
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        res = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // a != b
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            if (res[a][b] == 0 || (res[a][b] != 0 && res[a][b] > c)) {
                res[a][b] = c; // 양방향인줄 착각함ㅠㅠ
            }
        }
    }

    static void print_res() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(res[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        init_res();
        // 플로이드 워셜
        for (int k = 0; k < n; k++) {
            for (int s = 0; s < n; s++) {
                if (k == s)
                    continue;
                for (int e = 0; e < n; e++) {
                    if (s == e || k == e)
                        continue;
                    if (res[s][k] == 0 || res[k][e] == 0)
                        continue;
                    if (res[s][e] == 0 || (res[s][e] != 0 && res[s][e] > res[s][k] + res[k][e]))
                        res[s][e] = res[s][k] + res[k][e];
                }
            }
        }
        print_res();
    }
}
// MAX 값 설정 안하고 MAX==0으로 취급하고 진행했는데 원하는 답이 안나왔다.
// Math.min(res[s][e], res[s][k] + res[k][e]);
// 여기서 애초에 res[s][e]==0 이면 0이된다,,,;;;ㅎㅎㅎㅎㅎㅎㅎ
// 0으로 할거면 min을 못쓴다!
// 불필요한 연산을 줄이고 데이터 일관성(출력할 때 편할라고)을 가지기 위해서
// 위처럼 했는데 대신 코드가 좀 복잡해진다...