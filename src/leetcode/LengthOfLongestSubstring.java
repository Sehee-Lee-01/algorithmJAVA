package leetcode;

import java.util.Map;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) {
            return s.length();
        }

        char[] chars = s.toCharArray();
        HashMap<Character, Boolean> charMap = new HashMap<>();

        // 한 번 탐색해서 각 char의 마지막 인덱스 찾기
        for (int i = 0; i < chars.length; i++) {
            Boolean characterInfo = charMap.get(chars[i]);
            if (characterInfo == null) {
                charMap.put(chars[i], false);
            }
        }

        // 다시 돌면서 최대 길이 찾기
        // 위의 charMap에서 true, false로 사용여부 확인하기
        Deque<Character> deque = new ArrayDeque<>();
        int maxLen = 0;
        for (int i = 0; i < chars.length; i++) {
            boolean currCharInfo = charMap.get(chars[i]);
            if (!currCharInfo) {
                charMap.put(chars[i], true);
                deque.addLast(chars[i]);
            } else {
                while (deque.getFirst() != chars[i]) {
                    charMap.put(deque.pollFirst(), false);
                }
                deque.pollFirst();
                deque.addLast(chars[i]);
            }
            if (maxLen < deque.size()) {
                maxLen = deque.size();
            }
        }

        return maxLen;
    }
}