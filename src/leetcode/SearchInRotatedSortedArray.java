package leetcode;

class Solution {
    static int realStart;
    static int length;

    public int search(int[] nums, int target) {
        length = nums.length;

        // 시작잠 칮고
        seekRealStart(nums);

        // 타켓 찾기
        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[convertIdx(mid)] < target) {
                start = mid + 1;
            } else if (nums[convertIdx(mid)] > target) {
                end = mid - 1;
            } else {
                return convertIdx(mid);
            }
        }

        return -1;
    }

    int seekRealStart(int[] nums) {
        realStart = 0;
        int tmpEnd = nums.length - 1;

        while (realStart < tmpEnd) {
            int mid = (realStart + tmpEnd) / 2;
            if (nums[mid] > nums[tmpEnd]) {
                realStart = mid + 1;
            } else {
                tmpEnd = mid;
            }
        }

        return realStart;
    }

    int convertIdx(int idx) {
        if (idx < length - realStart) {
            return idx + realStart;
        }

        return idx - (length - realStart);
    }
}