package beakjoon;

import java.io.*;
import java.util.*;

class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        Map<String, Boolean> carOrders = new HashMap<>();
        Deque<String> q = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            String car = br.readLine();
            carOrders.put(car, false);
            q.add(car);
        }

        int res = 0;

        for (int i = 0; i < N; i++) {
            String outCar = br.readLine();
            if (carOrders.get(outCar)) {
                continue;
            }

            if (!outCar.equals(q.peekFirst())) {
                q.removeIf(a -> a.equals(outCar));

                carOrders.put(outCar, true);
                res++;
            } else {
                q.pollFirst();
            }
        }

        System.out.println(res);
    }

}
