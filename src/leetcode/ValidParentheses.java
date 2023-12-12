package leetcode;

import java.util.Deque;
import java.util.LinkedList;

public class ValidParentheses {
    public boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Deque<Character> stack = new LinkedList<>();

        for (char e : chars) {
            if (e == '(' || e == '{' || e == '[') {
                stack.push(e);
            } else {
                if (stack.isEmpty())
                    return false;
                Character peek = stack.poll();
                if ((e == ')' && peek == '(') ||
                        (e == '}' && peek == '{') ||
                        (e == ']' && peek == '[')) {
                    continue;
                }
                return false;
            }
        }
        if (!stack.isEmpty())
            return false;
        return true;
    }
}
