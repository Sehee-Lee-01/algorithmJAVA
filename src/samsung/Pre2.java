package samsung;

import java.util.*;
import java.io.*;

class Solution {
    static class Power {
        int value;
        int type;

        Power(int value, int type) {
            this.value = value;
            this.type = type;
        }
    }

    static class Person {
        final Power[] power;
        final int[] powerLoss;
        final int sum;

        Person(int[] powerArr) {
            sum = powerArr[0] + powerArr[1] + powerArr[2];
            power = new Power[3];
            powerLoss = new int[3];
            for (int i = 0; i < 3; i++) {
                power[i] = new Power(powerArr[i], i);
            }
            Arrays.sort(power, (a, b) -> (b.value - a.value));
            for (int i = 0; i < 3; i++) {
                powerLoss[i] = sum - power[i].value;
            }
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static int T, N;
    static int[] selectCnt;
    static Person[] persons;
    static int minLoss, currLoss;

    public static void main(String args[]) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            sb.append("#").append(test_case).append(" ")
                    .append(seekMinLoss()).append("\n");
        }

        System.out.println(sb.toString());
    }

    private static int seekMinLoss() throws IOException {
        N = Integer.parseInt(br.readLine());

        selectCnt = new int[3];
        persons = new Person[N];
        minLoss = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int[] power = new int[3];
            for (int j = 0; j < 3; j++) {
                power[j] = Integer.parseInt(st.nextToken());
                minLoss += power[j];
            }
            persons[i] = new Person(power);
        }

        if (N < 3) {
            return -1;
        }

        currLoss = 0;

        Arrays.sort(persons, (a, b) -> {
            for (int i = 0; i < 3; i++) {
                if (a.power[i].value != b.power[i].value)
                    return (b.power[i].value - a.power[i].value);
                if (a.power[i].value / a.powerLoss[i] != b.power[i].value / b.powerLoss[i])
                    return (b.power[i].value / b.powerLoss[i] - a.power[i].value / a.powerLoss[i]);
            }
            return b.sum - a.sum;
        });

        seek(0);
        return minLoss;
    }

    private static void seek(int idx) {
        if (minLoss <= currLoss) {
            return;
        }

        if (idx == persons.length) {
            if (selectCnt[0] > 0 && selectCnt[1] > 0 && selectCnt[2] > 0) {
                minLoss = currLoss;
            }
            return;
        }

        Person currPerson = persons[idx];
        for (int i = 0; i < 3; i++) {
            selectCnt[currPerson.power[i].type]++;
            currLoss += currPerson.powerLoss[i];
            seek(idx + 1);
            currLoss -= currPerson.powerLoss[i];
            selectCnt[currPerson.power[i].type]--;
        }
    }
}

// 116