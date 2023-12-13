package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {
        Set<Character> sortedSet = getSortedString(s);
        for (char c : sortedSet) {
            String subStr = s.substring(s.indexOf(c));
            if (sortedSet.equals(getSortedString(subStr))) {
                return c + removeDuplicateLetters(subStr.replaceAll(String.valueOf(c), ""));
            }
        }
        return "";
    }

    private Set<Character> getSortedString(String s) {
        Set<Character> res = new TreeSet<>((c1, c2) -> c1 - c2);

        for (char c : s.toCharArray()) {
            res.add(c);
        }

        return res;
    }
}
