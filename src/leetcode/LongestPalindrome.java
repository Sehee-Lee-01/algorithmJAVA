package leetcode;

public class LongestPalindrome {
    public String longestPalindrome(String s) {
        String maxS = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String subS = s.substring(i, j + 1);
                if (maxS.length() < subS.length()) {
                    int subSLen = subS.length() / 2;
                    boolean isMatch = true;
                    for (int k = 0; k < subSLen; k++) {
                        if (subS.charAt(k) != subS.charAt(subS.length() - k - 1)) {
                            isMatch = false;
                            break;
                        }
                    }

                    if (isMatch)
                        maxS = subS;
                }
            }
        }
        return maxS;
    }
}
