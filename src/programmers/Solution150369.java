package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution150369 {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        Queue<Integer> locStD = new LinkedList<>();
        Queue<Integer> locStP = new LinkedList<>();

        int sumD = 0;
        int sumP = 0;

        int disD = -1;
        int disP = -1;
        for (int i = n - 1; i >= 0; i--) {

            if (deliveries[i] != 0) {
                if (disD == -1 && sumD == 0) {
                    disD = i + 1;
                    locStD.add(disD);
                }

                if (cap > sumD) {
                    if (cap - sumD == deliveries[i]) {
                        sumD = 0;
                        disD = -1;
                    } else if (cap - sumD < deliveries[i]) {
                        disD = i + 1;
                        locStD.add(disD);
                        deliveries[i] -= cap - sumD;
                        int rep = deliveries[i] / cap - 1;
                        sumD = deliveries[i] % cap;
                        if (sumD != 0) {
                            rep++;
                        }
                        while (rep-- > 0) {
                            locStD.add(disD);
                        }
                        if (sumD == 0) {
                            disD = -1;
                        }
                    } else {
                        sumD += deliveries[i];
                    }
                }

            }

            if (pickups[i] != 0) {
                if (disP == -1 && sumP == 0) {
                    disP = i + 1;
                    locStP.add(disP);
                }

                if (cap > sumP) {
                    if (cap - sumP == pickups[i]) {
                        sumP = 0;
                        disP = -1;
                    } else if (cap - sumP < pickups[i]) {
                        disP = i + 1;
                        locStP.add(disP);
                        pickups[i] -= cap - sumP;
                        int rep = pickups[i] / cap - 1;
                        sumP = pickups[i] % cap;
                        if (sumP != 0)
                            rep++;
                        while (rep-- > 0)
                            locStP.add(disP);
                        if (sumP == 0)
                            disP = -1;
                    } else {
                        sumP += pickups[i];
                    }
                }
            }
        }

        long answer = 0;
        if (locStD.size() > locStP.size()) {
            while (!locStD.isEmpty()) {
                int dD = locStD.poll();
                // System.out.println("dD"+ dD);

                if (!locStP.isEmpty()) {
                    int dP = locStP.poll();
                    // System.out.println("dP"+ dP);

                    if (dD > dP)
                        answer += dD * 2;
                    else
                        answer += dP * 2;
                } else {
                    answer += dD * 2;
                }
            }
        } else {
            while (!locStP.isEmpty()) {
                int dP = locStP.poll();
                // System.out.println("dP"+ dP);

                if (!locStD.isEmpty()) {
                    int dD = locStD.poll();
                    // System.out.println("dD"+ dD);
                    if (dP > dD)
                        answer += dP * 2;
                    else
                        answer += dD * 2;
                } else {
                    answer += dP * 2;
                }
            }
        }

        return answer;
    }
}
