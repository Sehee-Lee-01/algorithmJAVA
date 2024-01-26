package leetcode;

class Solution {
    static int NO_RESULT = -1;

    public int[] twoSum(int[] numbers, int target) {
        for (int res1 = 0; res1 < numbers.length; res1++) {
            int tmpTarget = target - numbers[res1];
            int start = 0;
            int end = numbers.length - 1;

            if (numbers[res1] < tmpTarget) {
                if (numbers[numbers.length - 1] < tmpTarget)
                    continue;
                start = res1 + 1;
            } else if (tmpTarget < numbers[res1]) {
                if (numbers[0] > tmpTarget)
                    continue;
                end = res1 - 1;
            } else {
                if (res1 + 1 < numbers.length && numbers[res1 + 1] == tmpTarget)
                    return new int[] { res1 + 1, res1 + 2 };
                else if (res1 - 1 > -1 && numbers[res1 - 1] == tmpTarget)
                    return new int[] { res1, res1 + 1 };
                continue;
            }

            int res2 = seekTargetIsExist(start, end, tmpTarget, numbers);

            if (res2 != NO_RESULT)
                return (res1 < res2) ? new int[] { res1 + 1, res2 + 1 } : new int[] { res2 + 1, res1 + 1 };
        }

        return new int[2];
    }

    int seekTargetIsExist(int start, int end, int tmpTarget, int[] numbers) {

        while (start <= end) {
            int mid = (start + end) / 2;
            if (numbers[mid] < tmpTarget) {
                start = mid + 1;
            } else if (numbers[mid] > tmpTarget) {
                end = mid - 1;
            } else {
                return mid;
            }
        }

        return NO_RESULT;
    }
}