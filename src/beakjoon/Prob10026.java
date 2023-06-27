package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Prob10026 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] dir_y = { 0, 0, 1, -1 }, dir_x = { 1, -1, 0, 0 };
    static String[] map;
    static boolean[][] visited_rgb, visited_rb;
    static int region_rgb = 0, region_rb = 0;

    static void init_visited() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                visited_rgb[r][c] = false;
                visited_rb[r][c] = false;
            }
        }
    }

    static void seek_region_rgb(int r, int c, char color) {
        // r,g,b
        for (int i = 0; i < 4; i++) {
            int y = r + dir_y[i];
            int x = c + dir_x[i];
            if ((y >= 0 && y < N && x >= 0 && x < N) && (map[y].charAt(x) == color) && !visited_rgb[y][x]) {
                visited_rgb[y][x] = true;
                seek_region_rgb(y, x, color);
            }
        }
    }

    static boolean isBlue(char color) {
        if (color == 'B')
            return true;
        return false;
    }

    static void seek_region_rb(int r, int c, char color) {
        // (r,g),b
        for (int i = 0; i < 4; i++) {
            int y = r + dir_y[i];
            int x = c + dir_x[i];
            if ((y >= 0 && y < N && x >= 0 && x < N) && (isBlue(map[y].charAt(x)) == isBlue(color))
                    && !visited_rb[y][x]) {
                visited_rb[y][x] = true;
                seek_region_rb(y, x, color);
            }
        }
    }

    static void seek_regions() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char color = map[r].charAt(c);
                if (!visited_rgb[r][c]) {
                    // System.out.println("RGB++ r: " + r + " c: " + c);
                    region_rgb++;
                    visited_rgb[r][c] = true;
                    seek_region_rgb(r, c, color);
                }
                if (!visited_rb[r][c]) {
                    // System.out.println("(RG)B++ r: " + r + " c: " + c);
                    region_rb++;
                    visited_rb[r][c] = true;
                    seek_region_rb(r, c, color);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new String[N];
        visited_rgb = new boolean[N][N];
        visited_rb = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine();
        }

        init_visited();
        seek_regions();

        sb.append(region_rgb).append(" ").append(region_rb);
        System.out.println(sb.toString());
    }
}
