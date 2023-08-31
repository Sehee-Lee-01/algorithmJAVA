package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

// 18:12 ~ 19:27
// 갔던 곳 다시 갈 수도 있어서 잘 봐야함
// dfs 보단 bfs(최단 횟수니까!!!!)
public class Prob13549 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n, k, res, visit[];

    static void seek() {
        Deque<Integer> q = new ArrayDeque<>();

        visit[n] = 0;
        q.addFirst(n);

        while (!q.isEmpty()) {

            int curr = q.removeFirst();
            // System.out.println("visit " + curr);
            if (curr == k) {
                res = visit[curr];
                break;
            }

            int toVisit[] = { curr + 1, curr - 1, curr * 2 };
            int time[] = { visit[curr] + 1, visit[curr] + 1, visit[curr] };
            for (int i = 0; i < 3; i++) {
                if (toVisit[i] >= 0 && toVisit[i] <= 100000
                        && (visit[toVisit[i]] == -1 || visit[toVisit[i]] > time[i])) {
                    // 아래 설명보다는 디큐로 우선순위를 정하는 것이 더 정확하다
                    // 깨달음 전 설명
                    // +1 or -1로 이미 접근을 하면 visited 표시가된다.
                    // 근데 *2로 나중에 접근하면
                    // +1 or -1로 접근 방법보다 시간이 적게 걸리지만 표시를 못한다.
                    // 그래서 적은지도 비교를 해줘야한다.
                    // 그리고 bfs 우선순위도 중요하다.
                    visit[toVisit[i]] = time[i];
                    // 내가 큐에 들어가기 전에 값을 설정하도록 해놓아서
                    // 2로 답이 나올 수도 있는 것이 정답으로 이미 설정 되어있었음
                    if (i == 2)
                        q.addFirst(toVisit[i]);
                    else
                        q.addLast(toVisit[i]);
                    // System.out.println(toVisit[i] + " visit[toVisit[i]]: " + visit[toVisit[i]]);
                }
            }
        }
    }

    static void init_visit() {
        visit = new int[100001];
        for (int i = 0; i < visit.length; i++) {
            visit[i] = -1;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        res = Math.abs(n - k);
        init_visit();
        seek();

        System.out.println(res);
    }
}
// 더 정확한 방법은 디큐로 우선순위를 앞으로 보내는 것이다.!!!!!!!!!!!!!!!!!!!!
