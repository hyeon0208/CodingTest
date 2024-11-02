import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int[][] board = new int[19][19];
    private static int[] DX = {0, 1, 1, 1};
    private static int[] DY = {1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 19; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 19; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs();
    }

    private static void dfs() {
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] != 0) {
                    for (int d = 0; d < 4; d++) {
                        int px = i - DX[d];
                        int py = j - DY[d];
                        if (isValidRange(px, py) && board[i][j] == board[px][py]) {
                            continue;
                        }

                        int count = 1;
                        int nx = i + DX[d];
                        int ny = j + DY[d];

                        int startX = i;
                        int startY = j;

                        while (isValidRange(nx, ny) && board[nx][ny] == board[i][j]) {
                            // 왼쪽 아래 대각선 탐색의 경우, 더 왼쪽에 있는 좌표를 시작점으로 초기화
                            if (d == 3) {
                                startX = nx;
                                startY = ny;
                            }
                            count++;
                            nx += DX[d];
                            ny += DY[d];
                        }

                        if (count == 5) {
                            System.out.println(board[i][j]);
                            System.out.println((startX + 1) + " " + (startY + 1));
                            return;
                        }
                    }
                }
            }
        }
        System.out.println(0);
    }

    private static boolean isValidRange(int x, int y) {
        return x >= 0 && x < 19 && y >= 0 && y < 19;
    }
}

