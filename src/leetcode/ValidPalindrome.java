package leetcode;

import java.util.*;

public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        char[] sToChars = s.toCharArray();
        List<Character> elements = new ArrayList<>();
        for (char c : sToChars) {
            Character resElement = changeElementFormat(c);
            if (resElement != null) {
                elements.add(resElement);
            }
        }
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i) != elements.get(elements.size() - 1 - i))
                return false;
        }

        return true;
    }

    public Character changeElementFormat(char c) {
        if (Character.isLetter(c)) {
            return Character.toLowerCase(c);
        }
        if (Character.isDigit(c)) {
            return c;
        }
        return null;
    }
}