package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Prob1920 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ArrayList<Integer> Nlist = new ArrayList<>();
        for (int i = 0; i < N; i++)
            Nlist.add(Integer.parseInt(st.nextToken()));
        Nlist.sort(null);

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++)
            sb.append(binarySearch(Nlist, Integer.parseInt(st.nextToken()))).append("\n");

        System.out.println(sb);
    }

    static int binarySearch(ArrayList<Integer> list, int target) {
        int start = 0, end = list.size() - 1, mid = 0;
        while (start <= end) {
            mid = (start + end) / 2;
            if (list.get(mid) == target)
                return 1;
            else if (list.get(mid) > target)
                end = mid - 1;
            else
                start = mid + 1;
        }
        return 0;
    }
}
