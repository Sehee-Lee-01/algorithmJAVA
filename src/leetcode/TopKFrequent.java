package leetcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer> sorted = map.entrySet()
                .stream()
                .sorted((a, b) -> b.getValue() - a.getValue())
                .map(a -> a.getKey())
                .toList();

        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = sorted.get(i);
        }
        return res;
    }
}