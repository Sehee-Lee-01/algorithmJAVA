package leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            StringBuilder sb = new StringBuilder();
            for (char c : chars) {
                sb.append(c);
            }
            String groupValue = sb.toString();
            List<String> groupList = groups.get(groupValue);
            if (groupList != null) {
                groupList.add(s);
            } else {
                List<String> newGroupList = new LinkedList<>();
                newGroupList.add(s);
                groups.put(groupValue, newGroupList);
            }
        }

        List<List<String>> res = new LinkedList<>();
        groups.values().stream().forEach(res::add);
        return res;
    }
}
