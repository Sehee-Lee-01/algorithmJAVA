package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Prob11286 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static PriorityQueue<Integer> pq = new PriorityQueue<>(
            (o1, o2) -> {
                if(Math.abs(o1) == Math.abs(o2)) return o1 - o2;
                else return Math.abs(o1) - Math.abs(o2);
            }
    );
    static StringBuffer sb = new StringBuffer();
    public static void main(String[] args) throws IOException{
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                if(pq.isEmpty()) sb.append(0).append("\n");
                else sb.append(pq.poll()).append("\n");
            } else {
                pq.add(num);
            }
        }
        System.out.println(sb);
    }
}
