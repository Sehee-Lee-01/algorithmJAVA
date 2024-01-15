package leetcode;

import java.util.*;

class Solution {
    int targetSum;
    int[] nums, numCnts;
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        targetSum = target;
        nums = candidates;
        numCnts = new int[nums.length];
        seekCombination(0, 0);
        return res;
    }

    private void seekCombination(int stIdx, int sum) {
        if (sum > targetSum) {
            return;
        }
        if (sum == targetSum) {
            List<Integer> combination = new LinkedList<>();
            for (int i = 0; i < numCnts.length; i++) {
                int numCnt = numCnts[i];
                for (int j = 0; j < numCnt; j++) {
                    combination.add(nums[i]);
                }
            }
            res.add(combination);
        }

        for (int i = stIdx; i < nums.length; i++) {
            numCnts[i]++;
            seekCombination(i, sum + nums[i]);
            numCnts[i]--;
        }
    }
}