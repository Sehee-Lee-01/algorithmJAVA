package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Prob12891 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuffer sb = new StringBuffer();
    static HashMap<Character, Integer> dna_to_num = new HashMap<>() {
        {
            put('A', 0);
            put('C', 1);
            put('G', 2);
            put('T', 3);
        }
    };

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken()), P = Integer.parseInt(st.nextToken());

        String dna = br.readLine();
        st = new StringTokenizer(br.readLine());
        int[] dna_cnt = new int[4];
        for (int i = 0; i < 4; i++) // {A, C, G, T} 순서
            dna_cnt[i] = Integer.parseInt(st.nextToken());

        // idx=0 ~ P-1 까지 A, C, G, T 갯수 세기
        int[] curr_cnt = new int[4];
        for (int i = 0; i < P; i++)
            curr_cnt[dna_to_num.get(dna.charAt(i))]++;

        // 슬라이딩 윈도우
        int st = 0, cnt = 0;
        while (st + P <= S) {
            boolean isRight = true;
            for (int i = 0; i < 4; i++) {
                if (curr_cnt[i] < dna_cnt[i]) {
                    isRight = false;
                    break;
                }
            }
            if (isRight)
                cnt++;

            // 다음 칸으로 이동
            if (st == S - 1)
                break;
            curr_cnt[dna_to_num.get(dna.charAt(st++))]--;
            if (st + P > S)
                break;
            curr_cnt[dna_to_num.get(dna.charAt(st + P - 1))]++;
        }
        System.out.println(cnt);
    }
}