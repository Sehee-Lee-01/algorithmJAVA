package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Prob2346 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;

    static class Balloon {
        public int moveCnt;
        public int sec;

        public Balloon(int moveCnt, int sec) {
            this.moveCnt = moveCnt;
            this.sec = sec;
        }
    }

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());
        String[] strs = br.readLine().split(" ");
        Deque<Balloon> deque = new ArrayDeque<>();
        for (int i = 1; i < strs.length + 1; i++) {
            deque.add(new Balloon(Integer.parseInt(strs[i - 1]), i));
        }

        while (!deque.isEmpty()) {
            Balloon pang = deque.pollFirst();
            sb.append(pang.sec).append(" ");
            Balloon balloon;
            if (!deque.isEmpty()) {
                if (pang.moveCnt > 0) {
                    while (pang.moveCnt-- > 1) {
                        balloon = deque.pollFirst();
                        deque.addLast(balloon);
                    }
                } else {
                    while (pang.moveCnt++ < 0) {
                        balloon = deque.pollLast();
                        deque.addFirst(balloon);
                    }
                }
            }
        }

        System.out.println(sb.toString());
    }
}
