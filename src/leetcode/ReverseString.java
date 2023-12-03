package leetcode;

public class ReverseString {
    public void reverseString(char[] s) {
        int halfCnt = s.length / 2;
        for (int i = 0; i < halfCnt; i++) {
            char tmp = s[i];
            s[i] = s[s.length - 1 - i];
            s[s.length - 1 - i] = tmp;
        }
    }
}
