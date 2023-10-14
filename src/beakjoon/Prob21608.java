package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

// 21:04
class Student {
    int num;
    int[] fnd = new int[4];
    int[] loc;

    public Student(int num, int[] fnd, int[] loc) {
        this.num = num;
        this.fnd = fnd;
        this.loc = loc;
    }
}

public class Prob21608 {
    static final BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final HashMap<Integer, Student> MAP = new HashMap<>();
    static final int[] R_DIR = { -1, 0, 1, 0 };
    static final int[] C_DIR = { 0, -1, 0, 1 };
    static final int[] SCORE = { 0, 1, 10, 100, 1000 };
    static int N;
    static int[][] blankCnt;
    static int[][] stdLoc;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(BR.readLine());
        init();

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(BR.readLine());

            int num = Integer.parseInt(st.nextToken());

            int[] fnd = new int[4];
            for (int j = 0; j < 4; j++)
                fnd[j] = Integer.parseInt(st.nextToken());

            int[] loc = seekLoc(fnd);
            stdLoc[loc[0]][loc[1]] = num;
            updateBlank(loc[0], loc[1]);

            Student student = new Student(num, fnd, loc);
            MAP.put(student.num, student);

            // show();
        }

        // satisfy
        System.out.println(score());
    }

    public static int score() {
        int score = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Student student = MAP.get(stdLoc[i][j]);
                int fCnt = 0;
                for (int k = 0; k < 4; k++) {
                    int r = student.loc[0] + R_DIR[k];
                    int c = student.loc[1] + C_DIR[k];
                    if ((r > -1 && r < N) && (c > -1 && c < N)) {
                        for (int l = 0; l < 4; l++) {
                            if (student.fnd[l] == stdLoc[r][c]) {
                                fCnt++;
                            }
                        }
                    }
                }
                score += SCORE[fCnt];
            }
        }
        return score;
    }

    public static int[] seekLoc(int[] f) {
        int[][] fStdCntLoc = new int[N][N];

        // count
        for (int i = 0; i < 4; i++) {
            Student fS = MAP.get(f[i]);
            if (fS == null)
                continue;
            for (int j = 0; j < 4; j++) {
                int r = fS.loc[0] + R_DIR[j];
                int c = fS.loc[1] + C_DIR[j];
                if ((r > -1 && r < N) && (c > -1 && c < N))
                    fStdCntLoc[r][c]++;
            }
        }

        // seekMax
        int[] res = { -1, -1 };
        int max = -1;
        int maxB = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (stdLoc[i][j] == -1) {
                    if (fStdCntLoc[i][j] > max) {
                        max = fStdCntLoc[i][j];
                        maxB = blankCnt[i][j];
                        res[0] = i;
                        res[1] = j;
                    } else if (fStdCntLoc[i][j] == max && maxB < blankCnt[i][j]) {
                        maxB = blankCnt[i][j];
                        res[0] = i;
                        res[1] = j;
                    }
                }
            }

        }
        return res;
    }

    public static void init() {
        blankCnt = new int[N][N];
        stdLoc = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                stdLoc[i][j] = -1;
                int b = 0;
                if (i > 0)
                    b++;
                if (i < N - 1)
                    b++;
                if (j > 0)
                    b++;
                if (j < N - 1)
                    b++;
                blankCnt[i][j] = b;
            }
        }
    }

    public static void updateBlank(int r, int c) {
        for (int i = 0; i < 4; i++) {
            int uR = r + R_DIR[i];
            int uC = c + C_DIR[i];
            if ((uR > -1 && uR < N) && (uC > -1 && uC < N))
                blankCnt[uR][uC]--;
        }
    }

    public static void show() {
        System.out.println("++++++++++++ST++++++++++++");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(stdLoc[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("+++++++++++++++BL+++++++++++++++");

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(blankCnt[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("++++++++++++++EN+++++++++++++++");

    }
}
