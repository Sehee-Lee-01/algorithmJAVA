package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prob15686 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M, min = -1;
    static int[][] map; // 0: 빈칸, 1: 집, 2: 치킨집
    static ArrayList<int[]> houses = new ArrayList<>();
    static ArrayList<int[]> shops = new ArrayList<>();

    static void calculate_chicks(boolean[] is_opened) {
        int total = 0;
        // 맵에 오픈된 치킨집 그리기
        for (int i = 0; i < shops.size(); i++) {
            int shop_y = shops.get(i)[0];
            int shop_x = shops.get(i)[1];
            if (is_opened[i] == true) {
                map[shop_y][shop_x] = 2;
            } else {
                map[shop_y][shop_x] = 0;
            }
        }
        // 각 집마다 거리 구하기 실행(누적)
        for (int i = 0; i < houses.size(); i++) {
            int[] house_cor = houses.get(i);
            int house_y = house_cor[0], house_x = house_cor[1];
            int chick_dis = -1;
            for (int j = 0; j < shops.size(); j++) {
                if (is_opened[j]) {
                    int[] shop_cor = shops.get(j);
                    int shop_y = shop_cor[0], shop_x = shop_cor[1];

                    int tmp = Math.abs(shop_y - house_y) + Math.abs(shop_x - house_x);
                    if (chick_dis < 0 || chick_dis > tmp)
                        chick_dis = tmp;
                }
            }

            total += chick_dis;
        }

        // 최솟값과 비교
        if (min < 0 || min > total)
            min = total;

        return;
    }

    static void make_set(boolean[] is_opened, int shop_idx, int open_cnt) {
        if (open_cnt > M)
            return;
        // 오픈된 가게로 치킨 거리 구하기
        if (shop_idx == shops.size()) {
            if (open_cnt > 0)
                calculate_chicks(is_opened);
            return;
        }

        boolean[] tmp_is_opend = is_opened.clone();
        tmp_is_opend[shop_idx] = true;
        make_set(tmp_is_opend, shop_idx + 1, open_cnt + 1);

        tmp_is_opend[shop_idx] = false;
        make_set(tmp_is_opend, shop_idx + 1, open_cnt);
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    int[] tmp = { i, j };
                    houses.add(tmp);
                    continue;
                }
                if (map[i][j] == 2) {
                    int[] tmp = { i, j };
                    shops.add(tmp);
                }
            }
        }

        boolean[] is_opened = new boolean[shops.size()];
        make_set(is_opened, 0, 0);

        System.out.println(min);
    }
}
