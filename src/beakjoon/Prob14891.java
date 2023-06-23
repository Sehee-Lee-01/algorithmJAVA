package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob14891 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static String[] gears = new String[4];
    static int[] gears_top = { 0, 0, 0, 0 };
    static int K;

    static int[] both_idx(int gear_idx) {
        int[] res = { 0, 0 }; // right, left
        int top_idx = gears_top[gear_idx];

        res[0] = (top_idx + 2) > 7 ? (top_idx + 2) % 8 : (top_idx + 2);
        res[1] = (top_idx + 6) > 7 ? (top_idx + 6) % 8 : (top_idx + 6);

        return res;
    }

    static void move_gear(int num, int dir) {
        int gear_idx = num - 1;
        int[] move_dir = { 0, 0, 0, 0 }; // 1: clockwise, -1:counterclockwise, 0:no
        move_dir[gear_idx] = dir;

        // seek left
        for (int i = gear_idx; i > 0; i--) {
            int[] gear_both = both_idx(i);
            int[] lef_gear_both = both_idx(i - 1);
            if (gears[i].charAt(gear_both[1]) != gears[i - 1].charAt(lef_gear_both[0])) {
                move_dir[i - 1] -= move_dir[i];
            } else
                break;
        }
        // seek right
        for (int i = gear_idx; i < 3; i++) {
            int[] gear_both = both_idx(i);
            int[] rig_gear_both = both_idx(i + 1);
            if (gears[i].charAt(gear_both[0]) != gears[i + 1].charAt(rig_gear_both[1])) {
                move_dir[i + 1] -= move_dir[i];
            } else
                break;
        }
        // move
        for (int i = 0; i < 4; i++) {
            gears_top[i] -= move_dir[i];
            if (gears_top[i] < 0)
                gears_top[i] = 7;
            if (gears_top[i] > 7)
                gears_top[i] = 0;
        }
    }

    static int check_score() {
        int total = 0;
        int[] score = { 1, 2, 4, 8 };
        for (int i = 0; i < 4; i++) {
            if (gears[i].charAt(gears_top[i]) == '1')
                total += score[i];
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 4; i++)
            gears[i] = br.readLine().trim(); // 0: N, 1: S

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken()); // gear number
            int dir = Integer.parseInt(st.nextToken()); // 1: clockwise, -1:counterclockwise
            move_gear(num, dir);
        }

        int ans = check_score();
        System.out.println(ans);
    }
}
