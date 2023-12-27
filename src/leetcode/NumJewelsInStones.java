package leetcode;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : stones.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int sumJewels = 0;
        for (char c : jewels.toCharArray()) {
            sumJewels += map.getOrDefault(c, 0);
        }
        return sumJewels;
    }
}