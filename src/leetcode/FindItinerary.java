package leetcode;

import java.util.*;

class Solution {

    public List<String> findItinerary(List<List<String>> tickets) {
        Map<String, PriorityQueue<String>> ticketMap = new HashMap<>();

        // init
        for (List<String> ticket : tickets) {
            String start = ticket.get(0);

            ticketMap.putIfAbsent(start, new PriorityQueue<>());
            ticketMap.get(start).add(ticket.get(1));
        }

        // seek
        List<String> res = new LinkedList<>();

        Deque<String> travel = new ArrayDeque<>();
        travel.push("JFK");

        while (!travel.isEmpty()) {
            while (ticketMap.containsKey(travel.getFirst()) && !ticketMap.get(travel.getFirst()).isEmpty()) {
                travel.push(ticketMap.get(travel.getFirst()).poll());
            }

            res.add(0, travel.pop());
        }

        return res;
    }
}
