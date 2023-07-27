package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob9251 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String tmp;
    static char str1[], str2[];
    static int LCS[][];

    static void init() {
        LCS = new int[str1.length][str2.length];
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length; j++) {
                LCS[i][j] = -1;
            }
        }
    }

    static int get_LCS(int idx1, int idx2) {
        if (idx1 == -1 || idx2 == -1) {
            return 0;
        }
        // if (idx1 == 0 || idx2 == 0) {
        // return (str1[idx1] == str2[idx2]) ? 1 : 0;
        // }
        if (LCS[idx1][idx2] == -1)
            LCS[idx1][idx2] = (str1[idx1] == str2[idx2]) ? get_LCS(idx1 - 1, idx2 - 1) + 1
                    : Math.max(get_LCS(idx1 - 1, idx2), get_LCS(idx1, idx2 - 1));
        return LCS[idx1][idx2];
    }

    public static void main(String[] args) throws IOException {
        // init
        tmp = br.readLine();
        str1 = new char[tmp.length()];
        for (int i = 0; i < tmp.length(); i++) {
            str1[i] = tmp.charAt(i);
        }
        tmp = br.readLine();
        str2 = new char[tmp.length()];
        for (int i = 0; i < tmp.length(); i++) {
            str2[i] = tmp.charAt(i);
        }
        init();

        System.out.println(get_LCS(str1.length - 1, str2.length - 1));
    }
}
// top-down 방식으로 구현
// 왜 주석 단 부분으로는 안되는 것일까!!