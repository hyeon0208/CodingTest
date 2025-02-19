import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1}; // 상, 상좌, 좌, 좌하, 하, 하우, 우, 상우
    private static final int[] DY = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int BLANK = 0;
    private static final int SHARK = -1;


    private static int[][] maps;
    private static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        maps = new int[4][4];
        Fish[] fishes = new Fish[17];
        for (int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int fishNum = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken()) - 1;
                fishes[fishNum] = new Fish(i, j, direction, true);
                maps[i][j] = fishNum;
            }
        }

        int eatFishNum = maps[0][0];
        maps[0][0] = SHARK;
        fishes[eatFishNum].alive = false;
        dfs(0, 0, fishes[eatFishNum].direction, eatFishNum, maps, fishes);

        System.out.println(maxSum);
    }

    // 초기에 상어는 0,0에 잇는 물고기를 먹고 물고기 이동이 끝날떄 까지 대기한다.

    // 물고기 이동
    // 1번 부터 순서대로 이동한다.
    // 물고기는 한 칸을 이동할 수 있고, 다른 물고기가 있는 칸으로만 이동할 수 없다.
    // 만약 이동할 수 없으면 이동할 수 있는 칸을 찾을 때 까지 45도 반시계 회전시킨다.
    // 그럼에도 이동할 수 없다면 이동하지 않는다.
    // 이동 시 서로의 위치를 바꾼다.

    // 물고기 이동이 끝나면 상어가 이동한다.
    // 상어는 방향에 있는 칸으로만 이동할 수 있는데 여러개의 칸을 이동할 수 있다.
    // 상어가 물고기가 있는 칸으로 이동하면 그 칸의 물고기를 먹고 물고기의 방향을 가진다.
    // 이동할 칸이 없으면 끝.
    private static void dfs(int sharkX, int sharkY, int sharkDir, int sum, int[][] maps, Fish[] fishes) {
        maxSum = Math.max(maxSum, sum);

        // 백트래킹을 위해 현재 상태 복사
        int[][] newMaps = new int[4][4];
        for (int i = 0; i < 4; i++) {
            newMaps[i] = maps[i].clone();
        }

        Fish[] newFishes = new Fish[17];
        for (int i = 1; i <= 16; i++) {
            newFishes[i] = new Fish(fishes[i].x, fishes[i].y, fishes[i].direction, fishes[i].alive);
        }

        // 물고기 이동
        moveFishes(newMaps, newFishes, sharkX, sharkY);

        // 상어 이동
        for (int i = 1; i <= 3; i++) { // 4 x 4 특성상 상어는 최대 3칸 이동 가능
            int nx = sharkX + DX[sharkDir] * i;
            int ny = sharkY + DY[sharkDir] * i;

            if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && newMaps[nx][ny] > 0) {
                // 상어 위치 이동
                int fishNum = newMaps[nx][ny];
                newMaps[sharkX][sharkY] = BLANK; // 현재 상어 위치를 빈칸으로 표시
                newMaps[nx][ny] = SHARK; // 다음 상어 위치 표시
                newFishes[fishNum].alive = false;

                dfs(nx, ny, newFishes[fishNum].direction, sum + fishNum, newMaps, newFishes);

                // 상어 위치 복원
                newMaps[sharkX][sharkY] = SHARK;
                newMaps[nx][ny] = fishNum;
                newFishes[fishNum].alive = true;
            }
        }
    }

    private static void moveFishes(int[][] maps, Fish[] fishes, int sharkX, int sharkY) {
        for (int i = 1; i <= 16; i++) {
            if (!fishes[i].alive) {
                continue;
            }

            int x = fishes[i].x;
            int y = fishes[i].y;
            int dir = fishes[i].direction;

            for (int d = 0; d < 8; d++) {
                int nd = (dir + d) % 8;
                int nx = x + DX[nd];
                int ny = y + DY[nd];

                if (nx >= 0 && nx < 4 && ny >= 0 && ny < 4 && !(nx == sharkX && ny == sharkY)) {
                    if (maps[nx][ny] > 0) { // 다음 이동 위치에 물고기가 있을 때 (교환)
                        int targetFish = maps[nx][ny];

                        // 교환할 물고기의 위치를 현재 위치로 업데이트
                        fishes[targetFish].x = x;
                        fishes[targetFish].y = y;

                        // 현재 물고기의 위치를 교환할 물고기의 위치와 방향으로 업데이트
                        fishes[i].x = nx;
                        fishes[i].y = ny;
                        fishes[i].direction = nd;

                        // 맵 상태 업데이트
                        maps[x][y] = targetFish;
                        maps[nx][ny] = i;
                    } else { // 다음 이동이 빈 칸일 경우
                        maps[x][y] = 0; // 현재 위치를 빈 칸으로
                        maps[nx][ny] = i; // 새 위치에 현재 물고기 배치

                        // 새 위치의 물고기 정보 업데이트
                        fishes[i].x = nx;
                        fishes[i].y = ny;
                        fishes[i].direction = nd;
                    }
                    break; // if문을 탄 것은 이동이 성공한 것이므로 더 이상의 방향 전환 불필요
                }
            }
        }
    }

    private static class Fish {
        int x;
        int y;
        int direction;
        boolean alive;

        Fish(int x, int y, int direction, boolean alive) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.alive = alive;
        }
    }
}
