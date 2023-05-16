package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Prob7663 {
    // TreeMap 구조 사용, Treeset은 중복허용이 안된다.
    // C++ 에서는 multiset 사용 or priority_queue min, max 사용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            for (int i = 0; i < k; i++) {
                String[] input = br.readLine().split(" ");
                int n = Integer.parseInt(input[1]);
                if (input[0].equals("I")) {
                    // insert
                    if (tm.containsKey(n)) {
                        tm.put(n, tm.get(n) + 1);
                    } else
                        tm.put(n,1);
                } else {
                    // delete
                    if (!tm.isEmpty()) {
                        if (n == 1) {
                            if (tm.get(tm.lastKey()) > 1)
                                tm.put(tm.lastKey(), tm.get(tm.lastKey()) - 1);   
                            else 
                                tm.pollLastEntry(); // 최댓값 삭제
                        } else {
                            if (tm.get(tm.firstKey()) > 1)
                                tm.put(tm.firstKey(), tm.get(tm.firstKey()) - 1);   
                            else 
                                tm.pollFirstEntry(); // 최솟값 삭제 
                        }
                    }
                }
            }
            if (tm.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                System.out.println(tm.lastKey() + " " + tm.firstKey());
            }
        }
    }
}

    // 시간 초과
    // static void removeMaxInMinHeap(PriorityQueue<Integer> minHeap, int max) {
    //     ArrayList <Integer> temp = new ArrayList <>();
    //     boolean flag = false;
    //     while (!minHeap.isEmpty()) {
    //         int n = minHeap.poll();
    //         if (n == max && !flag) {
    //             flag = true;
    //             continue;
    //         }
    //         temp.add(n);
    //     }
    //     while(!temp.isEmpty()) {
    //         minHeap.add(temp.remove(0));
    //     }
    // }
    // static void removeMinInMaxHeap(PriorityQueue<Integer> maxHeap, int min) {
    //     ArrayList <Integer> temp = new ArrayList <>();
    //     boolean flag = false;
    //     while (!maxHeap.isEmpty()) {
    //         int n = maxHeap.poll();
    //         if (n == min && !flag) {
    //             flag = true;
    //             continue;
    //         }
    //         temp.add(n);
    //     }
    //     while(!temp.isEmpty()) {
    //         maxHeap.add(temp.remove(0));
    //     }    
    // }

    // public static void main(String[] args) throws IOException {
    //     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    //     int T = Integer.parseInt(br.readLine());
    //     for (int tc = 0; tc < T; tc++) {
    //         int k = Integer.parseInt(br.readLine());
    //         PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //         PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    //         for (int i = 0; i < k; i++) {
    //             String[] input = br.readLine().split(" ");
    //             int n = Integer.parseInt(input[1]);
    //             if (input[0].equals("I")) {
    //                 // insert
    //                 minHeap.add(n);
    //                 maxHeap.add(n);
    //             } else {
    //                 // delete
    //                 if (!minHeap.isEmpty() && !maxHeap.isEmpty()) {
    //                     if (n == 1) {
    //                         int max = maxHeap.poll(); // 최댓값 삭제
    //                         removeMaxInMinHeap(minHeap, max);
    
    //                     } else {
    //                         int min =  minHeap.poll(); // 최솟값 삭제
    //                         removeMinInMaxHeap(maxHeap, min);  
    //                     }
    //                 }
    //             }
    //         }
    //         if (minHeap.isEmpty() || maxHeap.isEmpty()) {
    //             System.out.println("EMPTY");
    //         } else {
    //             System.out.println(maxHeap.peek() + " " + minHeap.peek());
    //         }
    //     }
    // }
