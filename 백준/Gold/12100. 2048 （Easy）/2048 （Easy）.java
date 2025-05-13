import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int LIMIT = 5;

    private static int N;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(board, 0);
        System.out.println(result);
    }

    // 깊이 우선 탐색으로 모든 가능한 이동 조합 탐색
    private static void dfs(int[][] board, int depth) {
        if (depth == LIMIT) {
            result = Math.max(getMaxBlockSize(board), result);
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] newBoard = move(board, dir);
            dfs(newBoard, depth + 1);
        }
    }

    // 보드를 주어진 방향으로 이동
    private static int[][] move(int[][] board, int dir) {
        int[][] newBoard = new int[N][N];
        for (int i = 0; i < N; i++) {
            newBoard[i] = board[i].clone();
        }

        boolean[][] merged = new boolean[N][N];

        // 0: 오른쪽, 1: 왼쪽, 2: 아래, 3: 위
        if (dir == 0) {  // 오른쪽
            for (int i = 0; i < N; i++) {
                for (int j = N - 2; j >= 0; j--) {
                    if (newBoard[i][j] != 0) {
                        int temp = j;
                        // 빈 칸이면 계속 이동
                        while (temp < N - 1 && newBoard[i][temp + 1] == 0) {
                            newBoard[i][temp + 1] = newBoard[i][temp];
                            newBoard[i][temp] = 0;
                            temp++;
                        }

                        // 같은 값이고 아직 합쳐지지 않았으면 합치기
                        if (temp < N - 1 && newBoard[i][temp + 1] == newBoard[i][temp] && !merged[i][temp + 1]) {
                            newBoard[i][temp + 1] *= 2;
                            newBoard[i][temp] = 0;
                            merged[i][temp + 1] = true;
                        }
                    }
                }
            }
        } else if (dir == 1) {  // 왼쪽
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < N; j++) {
                    if (newBoard[i][j] != 0) {
                        int temp = j;
                        while (temp > 0 && newBoard[i][temp - 1] == 0) {
                            newBoard[i][temp - 1] = newBoard[i][temp];
                            newBoard[i][temp] = 0;
                            temp--;
                        }

                        if (temp > 0 && newBoard[i][temp - 1] == newBoard[i][temp] && !merged[i][temp - 1]) {
                            newBoard[i][temp - 1] *= 2;
                            newBoard[i][temp] = 0;
                            merged[i][temp - 1] = true;
                        }
                    }
                }
            }
        } else if (dir == 2) {  // 아래
            for (int j = 0; j < N; j++) {
                for (int i = N - 2; i >= 0; i--) {
                    if (newBoard[i][j] != 0) {
                        int temp = i;
                        while (temp < N - 1 && newBoard[temp + 1][j] == 0) {
                            newBoard[temp + 1][j] = newBoard[temp][j];
                            newBoard[temp][j] = 0;
                            temp++;
                        }

                        if (temp < N - 1 && newBoard[temp + 1][j] == newBoard[temp][j] && !merged[temp + 1][j]) {
                            newBoard[temp + 1][j] *= 2;
                            newBoard[temp][j] = 0;
                            merged[temp + 1][j] = true;
                        }
                    }
                }
            }
        } else {  // 위
            for (int j = 0; j < N; j++) {
                for (int i = 1; i < N; i++) {
                    if (newBoard[i][j] != 0) {
                        int temp = i;
                        while (temp > 0 && newBoard[temp - 1][j] == 0) {
                            newBoard[temp - 1][j] = newBoard[temp][j];
                            newBoard[temp][j] = 0;
                            temp--;
                        }

                        if (temp > 0 && newBoard[temp - 1][j] == newBoard[temp][j] && !merged[temp - 1][j]) {
                            newBoard[temp - 1][j] *= 2;
                            newBoard[temp][j] = 0;
                            merged[temp - 1][j] = true;
                        }
                    }
                }
            }
        }

        return newBoard;
    }

    private static int getMaxBlockSize(int[][] board) {
        int size = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                size = Math.max(size, board[i][j]);
            }
        }
        return size;
    }
}
