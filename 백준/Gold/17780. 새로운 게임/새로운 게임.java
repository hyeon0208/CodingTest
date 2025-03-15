import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {0, 0, -1, 1}; // 우 좌 상 하
    private static final int[] DY = {1, -1, 0, 0};
    private static final int WHITE = 0;
    private static final int RED = 1;
    private static final int BLUE = 2;

    private static int N, K;
    private static int[][] maps;
    private static ArrayDeque<Unit>[][] board;
    private static Unit[] units;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        maps = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        board = new ArrayDeque[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = new ArrayDeque<>();
            }
        }

        units = new Unit[K + 1];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken()) - 1;

            Unit unit = new Unit(i + 1, x, y, direction);
            units[i + 1] = unit;
            board[x][y].addLast(unit);
        }

        int turnCount = 0;
        while (turnCount <= 1000) {
            turnCount++;
            if (executeTurn()) {
                System.out.println(turnCount);
                return;
            }
        }

        System.out.println(-1);
    }

    private static boolean executeTurn() {
        for (int i = 1; i <= K; i++) {
            Unit unit = units[i];
            int x = unit.x;
            int y = unit.y;

            // 맨 아래 말이 아니면 이동하지 않음
            if (board[x][y].getFirst().num != unit.num) {
                continue;
            }

            int nx = x + DX[unit.direction];
            int ny = y + DY[unit.direction];

            // 체스판을 벗어나는 경우 또는 파란색 칸
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || maps[nx][ny] == BLUE) {
                // 방향 반전
                unit.direction = (unit.direction % 2 == 0) ? unit.direction + 1 : unit.direction - 1;

                // 반대 방향으로 이동
                nx = x + DX[unit.direction];
                ny = y + DY[unit.direction];

                // 또 벗어나거나 파란색이면 이동하지 않음
                if (nx < 0 || nx >= N || ny < 0 || ny >= N || maps[nx][ny] == BLUE) {
                    continue;
                }
            }

            // 이동
            if (maps[nx][ny] == WHITE) {
                moveWhite(x, y, nx, ny);
            } else if (maps[nx][ny] == RED) {
                moveRed(x, y, nx, ny);
            }

            // 말이 4개 이상 쌓였는지 확인
            if (board[nx][ny].size() >= 4) {
                return true;
            }
        }

        return false;
    }

    private static void moveWhite(int x, int y, int nx, int ny) {
        ArrayDeque<Unit> moving = new ArrayDeque<>();

        while (!board[x][y].isEmpty()) {
            Unit u = board[x][y].pollFirst();
            u.x = nx;
            u.y = ny;
            moving.addLast(u);
        }

        // 새 위치에 쌓기
        while (!moving.isEmpty()) {
            board[nx][ny].addLast(moving.pollFirst());
        }
    }

    private static void moveRed(int x, int y, int nx, int ny) {
        ArrayDeque<Unit> moving = new ArrayDeque<>();

        // 현재 칸에서 모든 말 꺼내
        while (!board[x][y].isEmpty()) {
            Unit u = board[x][y].pollFirst();
            u.x = nx;
            u.y = ny;
            moving.addFirst(u);  // 역순으로 쌓기 위해 앞에 추가
        }

        // 새 위치에 쌓기 (역순)
        while (!moving.isEmpty()) {
            board[nx][ny].addLast(moving.pollFirst());
        }
    }

    private static class Unit {
        int num;
        int x;
        int y;
        int direction;

        public Unit(int num, int x, int y, int direction) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.direction = direction;
        }
    }
}
