package beakjoon;

import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class ClassTime {
        int st;
        int en;

        ClassTime(int st, int en) {
            this.st = st;
            this.en = en;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<ClassTime> pq = new PriorityQueue<>((a, b) -> {
            if (a.st != b.st)
                return a.st - b.st;
            else
                return a.en - b.en;
        });

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());
            pq.add(new ClassTime(S, T));
        }

        PriorityQueue<Integer> pq2 = new PriorityQueue<>();

        while (!pq.isEmpty()) {
            ClassTime classTime = pq.poll();

            if (pq2.isEmpty()) {
                pq2.add(classTime.en);
                continue;
            }

            Queue<Integer> tmp = new ArrayDeque<>();
            while (!pq2.isEmpty() && pq2.peek() > classTime.st) {
                tmp.add(pq2.poll());
            }

            if (!pq2.isEmpty()) {
                pq2.poll();
            }

            pq2.add(classTime.st);
            while (!tmp.isEmpty()) {
                pq2.add(tmp.poll());
            }
        }

        System.out.println(pq2.size());
    }

}
