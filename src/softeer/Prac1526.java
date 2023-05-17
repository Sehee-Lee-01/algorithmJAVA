package softeer;

import java.util.*;
import java.io.*;

public class Prac1526 {

    static String canMergeString(String st1, String st2, int str_len) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str_len; i++) {
            if (st1.charAt(i) == st2.charAt(i)) {
                sb.append(st1.charAt(i));
                continue;
            }
            if (st1.charAt(i) == '.' && st2.charAt(i) != '.') {
                sb.append(st2.charAt(i));
                continue;
            }
            if (st2.charAt(i) == '.' && st1.charAt(i) != '.') {
                sb.append(st1.charAt(i));
                continue;
            }
            return "N";
        }
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<String> inputs = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            inputs.add(br.readLine());
        }
        Collections.sort(inputs, Collections.reverseOrder()); 
        ArrayList<String> res = new ArrayList<>();
        // 메인 단어가 다른 한 단어라도 겹칠 수 있으면 삭제&병합, 아니면 그냥 넣기
        while(true) {
            boolean[] isMerge = new boolean[inputs.size()];
            for(int i=0; i<isMerge.length;i++) isMerge[i] = false;
            int total = 0;
            for (int i=0;i<inputs.size();i++) {
                if (isMerge[i]) continue;
                String mStr = inputs.get(i);
                int cntMerge = 0;
                for (int j=i+1;j<inputs.size();j++) {
                    String sStr = inputs.get(j);
                    String mergedStr = canMergeString(mStr, sStr, M);
                    if (mergedStr.equals("N")) {
                        continue;
                    }
                    res.add(mergedStr);
                    isMerge[j] = true;
                    cntMerge++;
                }
    
                if (cntMerge == 0) {
                    res.add(mStr);
                }
                total += cntMerge;
            }
            if (total == 0) break;
            inputs = new ArrayList(res); // 복사 조심하기
            res.clear();
        }
        System.out.println(inputs.size());
        // System.out.println(inputs);
    }
}
