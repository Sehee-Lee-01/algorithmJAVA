package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 10:35
public class Prob1522 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        char[] inputs = br.readLine().toCharArray();
        int bCnt = 0;
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i] == 'b')
                bCnt++;
        }

        int maxMatchBCnt = 0;
        for (int i = 0; i < inputs.length; i++) {
            char[] mask = new char[inputs.length];
            for (int j = i; j < i + bCnt; j++) {
                int cor = j;
                if (cor >= inputs.length)
                    cor = j - inputs.length;
                mask[cor] = 'b';
            }

            int matchBCnt = 0;
            for (int j = 0; j < inputs.length; j++) {
                if (mask[j] == 'b' && inputs[j] == 'b')
                    matchBCnt++;
            }
            if (matchBCnt > maxMatchBCnt)
                maxMatchBCnt = matchBCnt;
        }

        System.out.println(bCnt - maxMatchBCnt);
    }
}
