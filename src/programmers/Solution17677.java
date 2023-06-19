package programmers;

import java.util.HashMap;
import java.util.Map;

class Solution17677 {
    static Map<String, int[]> map = new HashMap<String, int[]>();

    static boolean check_word(String word) {
        for (int i = 0; i < 2; i++) {
            if (!((int) word.charAt(i) >= 65 && (int) word.charAt(i) <= 90)) {
                return false;
            }
        }
        return true;
    }

    static void make_set(String str, int n) {
        for (int i = 0; i < str.length() - 1; i++) {
            String tmp = str.substring(i, i + 2);
            if (check_word(tmp)) {
                if (map.get(tmp) == null) { // 없을 때
                    // System.out.println(tmp);
                    int[] arr = { 0, 0 };
                    arr[n]++;
                    map.put(tmp, arr);
                } else { // 있을 때
                    map.get(tmp)[n]++;
                }
            }
        }
    }

    static int jacard() {
        // 자카드 유사도 구하기
        int inter = 0;
        int sum = 0;
        // 해시텡블을 키 기준으로 순환하면서 교집합, 합집합 값을 구한다.
        for (Map.Entry<String, int[]> e : map.entrySet()) {
            int[] nums = e.getValue();
            // System.out.println(nums[0]);
            // System.out.println(nums[1]);
            int min = 0, max = 0;
            if (nums[0] > nums[1]) {
                max = nums[0];
                min = nums[1];
            } else {
                max = nums[1];
                min = nums[0];
            }
            inter += min;
            sum += max;
        }

        int ans = 0;
        if (sum == 0)
            ans = 65536;
        else {
            float tmp = inter;
            tmp /= sum;
            tmp *= 65536;
            System.out.println(tmp);
            ans = (int) tmp;
        }
        return ans;
    }

    public int solution(String str1, String str2) {
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        make_set(str1, 0);
        make_set(str2, 1);

        int answer = jacard();

        return answer;
    }
}