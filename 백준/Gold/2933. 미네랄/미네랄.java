import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/2933

    private static final char BLANK = '.';
    private static final char MINERAL = 'x';
    private static int r;
    private static int c;
    private static int n;
    private static char[][] maps;
    private static boolean[][] visited;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        maps = new char[r][c];
        for (int x = 0; x < r; x++) {
            String line = br.readLine();
            for (int y = 0; y < c; y++) {
                maps[x][y] = line.charAt(y);
            }
        }

        n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int height = r - Integer.parseInt(st.nextToken());

            if (i % 2 == 0) { // 짝수 (왼쪽 -> 오른쪽)
                for (int j = 0; j < c; j++) {
                    if (maps[height][j] == MINERAL) {
                        maps[height][j] = BLANK;
                        break;
                    }
                }
            } else { // 홀수 (오른쪽 -> 왼쪽)
                for (int j = c - 1; j >= 0; j--) {
                    if (maps[height][j] == MINERAL) {
                        maps[height][j] = BLANK;
                        break;
                    }
                }
            }
            dropFloatingClusters();
        }

        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                sb.append(maps[x][y]);
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void dropFloatingClusters() {
        visited = new boolean[r][c];

        // 바닥에 붙은 클러스터 체크
        for (int j = 0; j < c; j++) {
            if (maps[r - 1][j] == MINERAL && !visited[r - 1][j]) {
                markConnectedMinerals(r - 1, j);
            }
        }

        // 방문되지 않은 미네랄들이 떠있는 클러스터
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (maps[i][j] == MINERAL && !visited[i][j]) {
                    dropCluster(i, j);
                    return; // 한 번에 하나의 클러스터만 떨어짐
                }
            }
        }
    }

    // 바닥과 연결된 미네랄들을 모두 마킹
    private static void markConnectedMinerals(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c && !visited[nx][ny] && maps[nx][ny] == MINERAL) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
    }

    // 클러스터를 떨어뜨리기
    private static void dropCluster(int startX, int startY) {
        List<int[]> cluster = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        visited[startX][startY] = true;
        cluster.add(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < r && ny >= 0 && ny < c && !visited[nx][ny] && maps[nx][ny] == MINERAL) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny});
                    cluster.add(new int[]{nx, ny});
                }
            }
        }

        for (int[] mineral : cluster) {
            maps[mineral[0]][mineral[1]] = BLANK;
        }

        int dropDistance = calculateDropDistance(cluster);

        for (int[] mineral : cluster) {
            maps[mineral[0] + dropDistance][mineral[1]] = MINERAL;
        }
    }

    // 클러스터가 떨어질 수 있는 거리 계산
    private static int calculateDropDistance(List<int[]> cluster) {
        int minDrop = Integer.MAX_VALUE;
        for (int[] mineral : cluster) {
            int x = mineral[0];
            int y = mineral[1];

            int drop = 0;
            for (int i = x + 1; i < r; i++) {
                if (maps[i][y] == MINERAL) {
                    break;
                }
                drop++;
            }
            minDrop = Math.min(minDrop, drop); // 클러스터가 떨어질 수 있는 거리
        }
        return minDrop;
    }
}
