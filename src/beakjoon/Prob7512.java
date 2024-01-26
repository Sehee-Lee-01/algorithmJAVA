package beakjoon;

import java.util.*;
import java.io.*;

public class Prob7512 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static long[] sumList;
    static ArrayList<Integer> al = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        init();

        int tc = Integer.parseInt(br.readLine());
        for (int i = 1; i <= tc; i++) {
            // in
            int m = Integer.parseInt(br.readLine());
            Set<Integer> length = new TreeSet<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                length.add(-Integer.parseInt(st.nextToken()));
            }

            boolean isPossible = false;

            // solve
            for (int res : al) {
                boolean isPossibleAtRes = true;
                for (int lenMinus : length) {
                    int len = -lenMinus;
                    boolean isPossibleAtLen = false;
                    for (int st = 0; st < sumList.length - len; st++) {
                        long sum = sumList[st + len - 1];
                        sum -= (st == 0) ? 0 : sumList[st - 1];

                        if (res == sum) {
                            isPossibleAtLen = true;
                            break;
                        } else if (res < sum) {
                            break;
                        }
                    }
                    if (!isPossibleAtLen) {
                        isPossibleAtRes = false;
                        break;
                    }
                }
                if (isPossibleAtRes) {
                    isPossible = true;
                    sb.append("Scenario ").append(i).append(":\n")
                            .append(res).append("\n\n");
                    break;
                }
            }
            if (!isPossible) {
                sb.append("Scenario ").append(i).append(":\n")
                        .append("\n\n");
            }
        }
        System.out.print(sb.toString());
    }

    static void init() {
        // init
        boolean[] isNotPrime = new boolean[10000001];
        for (int i = 2; i < (10000001 / 2) + 1; i++) {
            if (!isNotPrime[i]) {
                int product = 2;
                while (i * product < 10000001) {
                    isNotPrime[i * product++] = true;
                }
            }
        }
        for (int i = 2; i < 10000001; i++) {
            if (!isNotPrime[i])
                al.add(i);
        }

        long sum = 0;
        sumList = new long[al.size()];
        for (int i = 0; i < al.size(); i++) {
            sum += al.get(i);
            sumList[i] = sum;
        }
    }
}