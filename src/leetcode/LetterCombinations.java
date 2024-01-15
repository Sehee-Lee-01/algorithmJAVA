package leetcode;

import java.util.*;

class Solution {
    static char[][] numToChar;
    static int[] digitsInt;
    static char[] combination;
    static List<String> res;

    public List<String> letterCombinations(String digits) {
        res = new LinkedList<>();
        if (digits.length() == 0)
            return res;

        init();

        digitsInt = new int[digits.length()];// input을 배열로 바꾼 것
        for (int i = 0; i < digitsInt.length; i++) {
            digitsInt[i] = digits.charAt(i) - '0';
        }

        combination = new char[digits.length()]; // 조합을 저장할 배열
        makeCombinations(0);

        return res;
    }

    void init() { // 전화번호 문자들 init
        char start = 'a';
        numToChar = new char[10][2];

        for (int i = 2; i <= 9; i++) {
            numToChar[i][0] = start;

            if ((2 <= i && i <= 6) || (i == 8)) {
                start += 2;
            } else {
                start += 3;
            }

            numToChar[i][1] = start;

            start++;
        }
    }

    private void makeCombinations(int idx) {
        if (idx == combination.length) {
            res.add(makeCombitoString());
            return;
        }

        int currNum = digitsInt[idx];
        for (char i = numToChar[currNum][0]; i <= numToChar[currNum][1]; i++) {
            combination[idx] = i;
            makeCombinations(idx + 1);
        }
    }

    private String makeCombitoString() {
        StringBuilder sb = new StringBuilder();
        for (char c : combination) {
            sb.append(c);
        }
        return sb.toString();
    }
}