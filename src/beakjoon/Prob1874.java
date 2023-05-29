package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob1874 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int[] stack = new int[100000];
    static int top = -1;
    static int recentPushNum = 0;

    static void push() {
        stack[++top] = ++recentPushNum;
        sb.append("+\n");
    }

    static int pop() {
        if (top == -1)
            return -1;
        sb.append("-\n");
        return stack[top--];
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            while (recentPushNum < arr[i]) {
                push();
            }
            if (pop() != arr[i]) {
                System.out.println("NO\n");
                return;
            }
        }
        System.out.println(sb);
    }
}
