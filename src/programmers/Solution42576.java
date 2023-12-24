package programmers;

import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        Map<String, Integer> completionMap = new HashMap<>();
        for (String person : completion) {
            Integer personCnt = completionMap.get(person);
            if (personCnt == null) {
                completionMap.put(person, 1);
                continue;
            }
            completionMap.put(person, personCnt + 1);
        }

        String answer = "";
        for (String person : participant) {
            Integer personCnt = completionMap.get(person);
            if (personCnt == null || personCnt == 0) {
                answer = person;
                break;
            }

            completionMap.put(person, personCnt - 1);
        }

        return answer;
    }
}