package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MostCommonWord {
    static final String[] regexList = { "!", "?", "'", ",", ";", "." };

    public String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        for (String regex : regexList) {
            paragraph = paragraph.replace(regex, " ");
        }

        String[] words = paragraph.split(" ");
        Map<String, Integer> wordsCnt = new HashMap<>();
        Set<String> bannedSet = new HashSet<>(Arrays.asList(banned));
        int macCnt = 0;
        String macCntWord = "";
        for (String word : words) {
            if (bannedSet.contains(word) || word.equals(""))
                continue;

            Integer retrievedWordCnt = wordsCnt.get(word);
            if (retrievedWordCnt != null) {
                wordsCnt.put(word, retrievedWordCnt + 1);
                retrievedWordCnt = retrievedWordCnt + 1;
            } else {
                wordsCnt.put(word, 1);
                retrievedWordCnt = 1;
            }

            if (retrievedWordCnt > macCnt) {
                macCnt = retrievedWordCnt;
                macCntWord = word;
            }
        }

        return macCntWord;
    }
}
