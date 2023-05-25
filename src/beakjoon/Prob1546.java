package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob1546 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] scores = new int[N];
        long sum = 0;
        int M = 0;
        for (int i=0; i<N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
            sum += scores[i]; // 합은 미리 구할 수 있다.
            if (scores[i] > M) M = scores[i];
        }
        System.out.println(sum*100.0/(N*M)); // double로 나누면 소수점이 나옴
    }
}
// 연산 결과 식도 생각해보자