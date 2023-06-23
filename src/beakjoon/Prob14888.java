package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14888 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, max, min;
    static boolean is_cal = false;
    static int[] A, opr = new int[4];

    static int calculate_opr(int[] array, int arr_idx, int idx_opr) {
        int res = 0;
        if (idx_opr == 0) {
            // +
            res += array[arr_idx++];
            res += array[arr_idx];
        } else if (idx_opr == 1) {
            // -
            res += array[arr_idx++];
            res -= array[arr_idx];
        } else if (idx_opr == 2) {
            // *
            res += array[arr_idx++];
            res *= array[arr_idx];
        } else {
            // /
            res += array[arr_idx++];
            res /= array[arr_idx];
        }
        return res;
    }

    static void seek_val(int[] array, int[] operate, int idx) {
        // idx가 n이면 남은 연산자는 N-1-n
        if (idx == N - 1) {
            if (!is_cal) {
                min = array[idx];
                max = array[idx];
                is_cal = true;
            }
            if (array[idx] > max)
                max = array[idx];
            if (array[idx] < min)
                min = array[idx];
        }

        for (int i = 0; i < 4; i++) {
            if (operate[i] == 0)
                continue;
            int[] tmp_opr = operate.clone();
            tmp_opr[i]--;
            int[] tmp_arr = array.clone();
            tmp_arr[idx + 1] = calculate_opr(tmp_arr, idx, i);
            seek_val(tmp_arr, tmp_opr, idx + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        // input
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 다 합치면 N-1개
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            opr[i] = Integer.parseInt(st.nextToken());
        }

        seek_val(A, opr, 0);

        System.out.println(max);
        System.out.println(min);
    }
}
// 연산자 우선순위 무시하고 앞애서부터 진행
