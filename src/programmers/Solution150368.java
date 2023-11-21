package programmers;

// 2:27 ~ 3: 27
// 정수 나누기 주의
import java.util.*;

class Solution150368 {
    static int discount[] = { 10, 20, 30, 40 }, mx_signup = 0, mx_money = 0, U, E, discountE[][], discountEU[][][];

    // 경우의 수 구해서 나중에 그 결과값을 비교하기
    static void check_res(int e_idx, int e_dis[], int users[][]) {
        if (e_idx == E) { // 가입, 매출액 확인
            int signup = 0, money = 0;

            // 소비자 별 매출액 세기, 가입자, 매출액 합계
            for (int i = 0; i < U; i++) {
                int user_cost = 0;
                for (int j = 0; j < E; j++)
                    user_cost += discountEU[e_dis[j]][j][i];
                if (users[i][1] <= user_cost)
                    signup++;
                else
                    money += user_cost;
            }

            if (mx_signup < signup) {
                mx_signup = signup;
                mx_money = money;
            } else if (mx_signup == signup && mx_money < money) {
                mx_money = money;
            }

            return;
        }

        for (int i = 0; i < 4; i++) {
            e_dis[e_idx] = i;
            check_res(e_idx + 1, e_dis, users);
        }
    }

    static int sale(int dis, int emo) {
        return emo * (100 - dis) / 100;
    }

    public int[] solution(int[][] users, int[] emoticons) {
        E = emoticons.length;
        U = users.length;
        discountE = new int[4][E];
        discountEU = new int[4][E][U];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < E; j++) {
                discountE[i][j] = sale(discount[i], emoticons[j]);
                for (int k = 0; k < U; k++) {
                    discountEU[i][j][k] = (discount[i] < users[k][0]) ? 0 : discountE[i][j];
                }
            }
        }

        int e_dis[] = new int[E];
        check_res(0, e_dis, users);

        int[] answer = new int[2];
        answer[0] = mx_signup;
        answer[1] = mx_money;
        return answer;
    }
}