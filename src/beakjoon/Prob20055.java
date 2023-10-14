package beakjoon;

//19:07
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob20055 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // belt size
        K = Integer.parseInt(st.nextToken()); // 내구도 0칸 개수 종료

        // init
        int[] beltDual = new int[2 * N + 1];
        boolean[] beltRobo = new boolean[2 * N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N * 2; i++) { // durability
            beltDual[i + 1] = Integer.parseInt(st.nextToken());
            beltRobo[i + 1] = false; //
        }

        // start
        int level = 0;
        int zeroD = 0;
        int up = 1;
        while (zeroD < K) {
            level++;
            up = (up == 1) ? 2 * N : up - 1;
            int down = (up + (N - 1) > 2 * N) ? up + (N - 1) - 2 * N : up + (N - 1);
            // exist robot
            for (int i = 0; i < N - 1; i++) {
                int loc = (down - i < 1) ? down - i + (2 * N) : down - i;
                if (beltRobo[loc]) {
                    if (loc == down) {
                        beltRobo[loc] = false;
                        continue;
                    }
                    int nex_loc = loc + 1 > 2 * N ? loc + 1 - (2 * N) : loc + 1;
                    if (!beltRobo[nex_loc] && beltDual[nex_loc] > 0) {
                        beltRobo[loc] = false;
                        beltDual[nex_loc]--;
                        if (beltDual[nex_loc] == 0)
                            zeroD++;
                        if (nex_loc != down)
                            beltRobo[nex_loc] = true;
                    }
                }
            }

            // up
            if (beltDual[up] > 0 && !beltRobo[up]) {
                beltRobo[up] = true;
                beltDual[up]--;
                if (beltDual[up] == 0)
                    zeroD++;
            }
        }

        System.out.println(level);
    }
}
