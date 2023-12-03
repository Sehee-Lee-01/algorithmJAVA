package leetcode;

import java.util.LinkedList;
import java.util.List;

public class ReorderDataInlLogFiles {
    public String[] reorderLogFiles(String[] logs) {
        List<String> digitLogs = new LinkedList<>();
        List<String> letterLogs = new LinkedList<>();
        for (String log : logs) {
            if (isDigitLog(log)) {
                digitLogs.add(log);
            } else {
                letterLogs.add(log);
            }
        }

        letterLogs.sort((a, b) -> {
            int split1 = a.indexOf(" ");
            String identifier1 = a.substring(0, split1);
            String content1 = a.substring(split1);

            int split2 = b.indexOf(" ");
            String identifier2 = b.substring(0, split2);
            String content2 = b.substring(split2);

            if (content1.equals(content2)) {
                return identifier1.compareTo(identifier2);
            }

            return isContentFront(content1, content2);
        });

        String[] res = new String[logs.length];
        for (int i = 0; i < letterLogs.size(); i++) {
            res[i] = letterLogs.get(i);
        }
        for (int i = letterLogs.size(); i < logs.length; i++) {
            res[i] = digitLogs.get(i - letterLogs.size());
        }
        return res;
    }

    public static int isContentFront(String logContent1, String logContent2) {
        String[] logContents1 = logContent1.split(" ");
        String[] logContents2 = logContent2.split(" ");
        boolean isFrontLong = (logContents1.length > logContents2.length);
        int maxIdx = isFrontLong ? logContents2.length : logContents1.length;
        for (int i = 0; i < maxIdx; i++) {
            int cmp = logContents1[i].compareTo(logContents2[i]);
            if (cmp != 0) {
                return cmp;
            }
        }

        return isFrontLong ? 1 : -1;
    }

    public static boolean isDigitLog(String log) {
        if (Character.isDigit(log.split(" ")[1].toCharArray()[0]))
            return true;
        return false;
    }
}
