package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 16:17
public class Prob1918 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String input;
    static StringBuilder sb = new StringBuilder();
    static char chars[];
    static Stack<Character> s = new Stack<>();

    public static void main(String[] args) throws IOException {
        input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') {
                s.add(c);
            } else if (c == ')') {
                while (s.peek() != '(')
                    sb.append(s.pop());
                s.pop();
            } else if (c == '+' || c == '-') {
                while (!s.isEmpty() && (s.peek() == '*' || s.peek() == '/' || s.peek() == '+' || s.peek() == '-'))
                    sb.append(s.pop());
                s.add(c);
            } else if (c == '*' || c == '/') {
                while (!s.isEmpty() && (s.peek() == '*' || s.peek() == '/'))
                    sb.append(s.pop());
                s.add(c);
            } else {
                sb.append(c);
            }
        }
        while (!s.isEmpty())
            sb.append(s.pop());

        System.out.println(sb.toString());
    }
}
// String에는 toCharArray() 기능이 있다.
// 요즘 재귀만 쓰다보니 스택을 어쩧게 적용하는지 꽤나 힘들었다...
// 자료구조를 어떻게 할지가 가장 시간이 오래 걸렸다.
// 문제 자체가 좀 어렵긴 했다. 후위표기식이 뭔지 잘 몰랐다.