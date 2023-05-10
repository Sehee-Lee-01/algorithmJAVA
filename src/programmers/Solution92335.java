package programmers;
import java.util.*;

class Solution92335 {
    static int cntPrime(String[] nums) {
        int res = 0;
        for (String numstr: nums) {
            if (numstr.equals("")) continue; // 빈 문자열인 경우
            
            long num = Long.parseLong(numstr); // Long을 쓰자
            if (num == 1) continue;
            if (num == 2) {
                res++;
                continue;
            }
            
            boolean isPrime = true;
            // for (int i=2; i<= Math.sqrt(num); i++) {
            for (long i=2; i*i <= num; i++) { // 제곱을 할거면 long을 쓰자
                if (num % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) res++;
        }
        return res;                                                                    
    }
    public int solution(int n, int k) {
        String kString = Integer.toString(n,k);// k진수로 바꾸기
        String[] kNums = kString.split("0");// 0으로 나누기
        int answer = cntPrime(kNums);// 돌면서 소수 확인
        return answer;
    }
}