package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        Map<Character, Integer> counter = new HashMap<>();
        Set<Character> seen = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();

        char[] chars = s.toCharArray();
        setCharCount(chars, counter);

        for (char c : chars) {
            counter.put(c, counter.get(c) - 1);
            if (seen.contains(c))
                continue;

            while (!stack.isEmpty() && stack.peek() > c && counter.get(stack.peek()) > 0) {
                seen.remove(stack.pop());
            }

            stack.push(c);
            seen.add(c);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }

    private void setCharCount(char[] chars, Map<Character, Integer> counter) {
        for (char c : chars) {
            counter.put(c, counter.getOrDefault(c, 0) + 1);
        }
    }
}
