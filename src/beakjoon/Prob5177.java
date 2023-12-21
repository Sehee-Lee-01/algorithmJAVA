package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class Prob5177 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int K;

    static final String MSG = "Data Set ";
    static final String EQUAL = ": equal";
    static final String NOT_EQUAL = ": not equal";

    public static void main(String[] args) throws IOException {
        K = Integer.parseInt(br.readLine());

        for (int i = 1; i < K + 1; i++) {
            String s1 = convert(br.readLine());
            String s2 = convert(br.readLine());

            sb.append(MSG)
                    .append(i)
                    .append(s1.equals(s2) ? EQUAL : NOT_EQUAL)
                    .append("\n\n");
        }

        System.out.println(sb.toString());
    }

    static String convert(String s) {
        s = s.trim()
                .toLowerCase()
                .replaceAll("[\\[\\{]", "(")
                .replaceAll("[\\}\\]]", ")")
                .replaceAll(";", ",");

        return convertToOneWhitespace(s);
    }

    static String convertToOneWhitespace(String str) {
        Deque<Character> q = new ArrayDeque<>();
        for (char c : str.toCharArray()) {
            if (!q.isEmpty()) {
                if (c == ' ' && (q.peekLast() == ' ' || isMatch(q.peekLast()))) {
                    continue;
                }
                if (isMatch(c) && q.peekLast() == ' ') {
                    q.pollLast();
                }
            }

            q.add(c);
        }

        return Arrays.toString(q.toArray());

    }

    static boolean isMatch(char c) {
        return c == '(' || c == ')' || c == ',' || c == ':' || c == '.';
    }

}
