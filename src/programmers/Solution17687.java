package programmers;
import java.util.*;

class Solution17687 {
    public String solution(int n, int t, int m, int p) {
        
        // 게임 할 때 말하는 숫자 연속으로 **미리** 저장(튜브가 n번 말할 수 있을 때 까지) 
        StringBuffer gameSecquenceBuffer = new StringBuffer();
        int num = 0;
        while(gameSecquenceBuffer.length() <= m*t)
           gameSecquenceBuffer.append(Integer.toString(num++,n));
        
        // 튜브가 말해야하는 숫자 출력
        StringBuffer resBuffer = new StringBuffer();
        String gameSecquence =  gameSecquenceBuffer.toString();
        for (int i=0; i< gameSecquence.length(); i++) { // m==p일 때는 조심해야한다.
            if (resBuffer.length() >= t) break;
            if ((i+1) % m == p ||(p==m)&&((i+1) % p == 0)) {
                resBuffer.append(gameSecquence.charAt(i));
            // System.out.println("resBuffer: " + resBuffer);            
            }
        }
        
        String answer = resBuffer.toString().toUpperCase();
        return answer;
    }
}