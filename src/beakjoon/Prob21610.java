package beakjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Prob21610 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[][] A;
    static boolean[][] C;
    static int N, M;

    static void print_cloud() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(C[i][j] ? "1" : "0");
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

    }

    static void print() throws IOException {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(String.valueOf(A[i][j]));
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();

    }

    static void move_cloud(int d, int s) {
        int[] d_y = { 0, -1, -1, -1, 0, 1, 1, 1 };
        int[] d_x = { -1, -1, 0, 1, 1, 1, 0, -1 };
        boolean[][] tmp_C = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tmp_C[i][j] = false;
            }
        }
        // 구름 이동, 경계넘어감
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (C[i][j]) {
                    int y = i + (d_y[d] * s);
                    int x = j + (d_x[d] * s);

                    // System.out.println(">>1 " + i + " " + j + " " + d + " " + s);
                    // System.out.println(">>2 " + y + " " + x);

                    if (Math.abs(y) >= N)
                        y %= N;
                    if (y < 0) {
                        y += N;
                    } else if (y >= N) {
                        y -= N;
                    }

                    if (Math.abs(x) >= N)
                        x %= N;
                    if (x < 0) {
                        x += N;
                    } else if (x >= N) {
                        x -= N;
                    }

                    // System.out.println(">>3 " + y + " " + x);
                    tmp_C[y][x] = true;
                }
            }
        }
        C = tmp_C;
    }

    static void rain() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (C[i][j])
                    A[i][j]++;
            }
        }
    }

    static void replicate_water() {
        int[] dir_y = { -1, -1, 1, 1 }, dir_x = { -1, 1, -1, 1 };
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (C[i][j]) {
                    for (int k = 0; k < 4; k++) {
                        // 각 칸마다 4 대각선 확인 후 있느 만큼 물 채우기
                        int y = i + dir_y[k];
                        int x = j + dir_x[k];
                        // 경계 안 넘어감
                        if ((y >= 0 && y < N) && (x >= 0 && x < N) && A[y][x] > 0)
                            A[i][j]++;
                    }
                }
            }
        }
    }

    static void make_cloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (C[i][j])
                    C[i][j] = false;
                else if (A[i][j] > 1) {
                    A[i][j] -= 2;
                    C[i][j] = true;
                }
            }
        }
    }

    static int count_water() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sum += A[i][j];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        C = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                C[i][j] = false;
            }
        }

        // make first cloud
        C[N - 1][0] = true;
        C[N - 1][1] = true;
        C[N - 2][0] = true;
        C[N - 2][1] = true;

        // move!
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            move_cloud(d - 1, s);
            // System.out.println("rain");
            rain();
            // print();

            // System.out.println("replicate_water");
            replicate_water();
            // print();
            // print_cloud();
            // System.out.println("make_cloud");
            make_cloud();
            // print_cloud();
        }
        System.out.println(count_water());
    }
}
