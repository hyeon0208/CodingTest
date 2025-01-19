import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    // 동(1), 서(2), 북(3), 남(4) 순서
    private static final int[] DX = {0, 0, 0, -1, 1};
    private static final int[] DY = {0, 1, -1, 0, 0};
    private static final int RIGHT = 1;
    private static final int LEFT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    private static int N;
    private static int M;
    private static int X;
    private static int Y;
    private static int K;
    private static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dice dice = new Dice();
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int direction = Integer.parseInt(st.nextToken());
            if (moveDice(dice, direction)) {
                sb.append(dice.getTop()).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean moveDice(Dice dice, int direction) {
        int nx = X + DX[direction];
        int ny = Y + DY[direction];

        if (0 <= nx && nx < N && 0 <= ny && ny < M) {
            dice.roll(direction);
            if (maps[nx][ny] == 0) {
                maps[nx][ny] = dice.getBottom();
            } else {
                dice.setBottom(maps[nx][ny]);
                maps[nx][ny] = 0;
            }
            X = nx;
            Y = ny;
            return true;
        }
        return false;
    }

    private static class Dice {
        private static final int TOP = 1; // 주사위 맨 위
        private static final int FRONT = 2; // 주사위 앞 면
        private static final int EAST = 3; // 주사위 오른쪽 면
        private static final int WEST = 4; // 주사위 왼쪽 면
        private static final int BACK = 5; // 주사위 뒷 면
        private static final int BOTTOM = 6; // 주사위 바닥

        private int[] dice;

        public Dice() {
            this.dice = new int[7];
        }

        public void roll(int direction) {
            int[] temp = dice.clone();

            if (direction == UP) {
                dice[TOP] = temp[FRONT];
                dice[FRONT] = temp[BOTTOM];
                dice[BOTTOM] = temp[BACK];
                dice[BACK] = temp[TOP];
            } else if (direction == DOWN) {
                dice[TOP] = temp[BACK];
                dice[FRONT] = temp[TOP];
                dice[BOTTOM] = temp[FRONT];
                dice[BACK] = temp[BOTTOM];
            } else if (direction == LEFT) {
                dice[TOP] = temp[EAST];
                dice[EAST] = temp[BOTTOM];
                dice[BOTTOM] = temp[WEST];
                dice[WEST] = temp[TOP];
            } else if (direction == RIGHT) {
                dice[TOP] = temp[WEST];
                dice[WEST] = temp[BOTTOM];
                dice[BOTTOM] = temp[EAST];
                dice[EAST] = temp[TOP];
            } else {
                throw new IllegalArgumentException("존재하지 않는 방향입니다.");
            }
        }

        public int getTop() {
            return dice[TOP];
        }

        public int getBottom() {
            return dice[BOTTOM];
        }

        public void setBottom(int value) {
            dice[BOTTOM] = value;
        }
    }
}
