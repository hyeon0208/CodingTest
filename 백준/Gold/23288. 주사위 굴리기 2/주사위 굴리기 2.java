import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int EAST = 0;
    private static final int SOUTH = 1;
    private static final int WEST = 2;
    private static final int NORTH = 3;

    // 동 남 서 북
    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};

    private static int N;
    private static int M;
    private static int K;
    private static int result;
    private static int curDirectionIndex;
    private static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        curDirectionIndex = EAST;
        result = 0;
        simulate();
        System.out.println(result);
    }

    private static void simulate() {
        int curX = 0;
        int curY = 0;
        Dice dice = new Dice();

        while (K-- > 0) {
            int nextX = curX + DX[curDirectionIndex];
            int nextY = curY + DY[curDirectionIndex];

            if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                curDirectionIndex = (curDirectionIndex + 2) % 4;
                nextX = curX + DX[curDirectionIndex];
                nextY = curY + DY[curDirectionIndex];
            }
            dice.roll(curDirectionIndex);
            curX = nextX;
            curY = nextY;
            result += getScore(curX, curY);
            determineDirection(dice, curX, curY);
        }
    }

    private static void determineDirection(Dice dice, int x, int y) {
        if (dice.getBOTTOM() > maps[x][y]) { // 90도 시계 방향으로 회전
            curDirectionIndex = (curDirectionIndex + 1) % 4;
        } else if (dice.getBOTTOM() < maps[x][y]) { // 90도 반시계 방향으로 회전시킨다.
            curDirectionIndex = (curDirectionIndex + 3) % 4;
        }
    }

    static int getScore(int x, int y) {
        int num = maps[x][y];
        int count = 1;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int i = 0; i < 4; i++) {
                int nx = cur[0] + DX[i];
                int ny = cur[1] + DY[i];

                if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
                    continue;
                }

                if(!visited[nx][ny] && maps[nx][ny] == num) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    count++;
                }
            }
        }
        return num * count;
    }

    private static class Dice {

        private final int TOP = 1;
        private final int BOTTOM = 2;
        private final int FRONT = 3;
        private final int BACK = 4;
        private final int RIGHT = 5;
        private final int LEFT = 6;

        private final int[] dice;

        public Dice() {
            this.dice = new int[7];
            dice[TOP] = 1;
            dice[BOTTOM] = 6;
            dice[FRONT] = 5;
            dice[BACK] = 2;
            dice[RIGHT] = 3;
            dice[LEFT] = 4;
        }

        public void roll(int direction) {
            int[] temp = dice.clone();

            if (direction == EAST) {
                dice[TOP] = temp[LEFT];
                dice[LEFT] = temp[BOTTOM];
                dice[BOTTOM] = temp[RIGHT];
                dice[RIGHT] = temp[TOP];
            } else if (direction == SOUTH) {
                dice[TOP] = temp[BACK];
                dice[BACK] = temp[BOTTOM];
                dice[BOTTOM] = temp[FRONT];
                dice[FRONT] = temp[TOP];
            } else if (direction == WEST) {
                dice[TOP] = temp[RIGHT];
                dice[RIGHT] = temp[BOTTOM];
                dice[BOTTOM] = temp[LEFT];
                dice[LEFT] = temp[TOP];
            } else {
                dice[TOP] = temp[FRONT];
                dice[FRONT] = temp[BOTTOM];
                dice[BOTTOM] = temp[BACK];
                dice[BACK] = temp[TOP];
            }
        }

        public int getBOTTOM() {
            return dice[BOTTOM];
        }
    }
}
