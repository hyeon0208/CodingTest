import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int L;
    private static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); // 경사로 길이

        maps = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 길에 속한 모든 칸의 높이가 모두 같아야 한다.
        // 경사로를 높을 수 있는 데 높이는 항상 1이며 길이는 L이다.
        // 경사로 높이차이는 1이어야 한다.
        // 경사로를 놓을 낮은 칸의 높이는 모두 같아야하고 L개의 칸이 연속되어야 한다.
        // 경사로는 겹칠 수 없다.
        int result = 0;
        for (int i = 0; i < N; i++) {
            if (checkRow(i)) {
                result++;
            }
            if (checkCol(i)) {
                result++;
            }
        }
        System.out.println(result);
    }

    private static boolean checkRow(int row) {
        boolean[] visited = new boolean[N]; // 경사로 설치 여부 체크

        for (int col = 1; col < N; col++) {
            int startPoint = maps[row][col - 1];
            int nextPoint = maps[row][col];
            int diff = nextPoint - startPoint;

            if (diff == 0) { // 높이가 같으면 패스
                continue;
            }

            if (Math.abs(diff) > 1) { // 높이 차이가 1보다 크면 실패
                return false;
            }

            if (diff == 1) { // 오르막 (다음지점이 더 클 경우)
                // 현재 위치(col)에서 왼쪽으로 경사로 길이 L만큼 탐색
                for (int j = 1; j <= L; j++) {
                    int nextSlopeIndex = col - j;
                    if (nextSlopeIndex < 0 || visited[nextSlopeIndex]) { // 범위를 벗어나거나 이미 경사로가 있으면 실패
                        return false;
                    }
                    if (startPoint != maps[row][nextSlopeIndex]) { // 경사로의 높이가 달라지면 실패
                        return false;
                    }
                    visited[nextSlopeIndex] = true;
                }
            } else { // 내리막 (시작지점이 더 클 경우)
                // 현재 위치(col)에서 오른쪽으로 경사로 길이 L만큼 탐색
                for (int j = 0; j < L; j++) {
                    int nextSlopeIndex = col + j;
                    if (nextSlopeIndex >= N || visited[nextSlopeIndex]) {
                        return false;
                    }
                    if (nextPoint != maps[row][nextSlopeIndex]) {
                        return false;
                    }
                    visited[nextSlopeIndex] = true;
                }
            }
        }
        return true;
    }

    private static boolean checkCol(int col) {
        boolean[] visited = new boolean[N];

        for (int row = 1; row < N; row++) {
            int startPoint = maps[row - 1][col];
            int nextPoint = maps[row][col];
            int diff = nextPoint - startPoint;

            if (Math.abs(diff) > 1) {
                return false;
            }

            if (diff == 0) {
                continue;
            }

            if (diff == 1) { // 오르막
                for (int j = 1; j <= L; j++) {
                    int nextSlopeIndex = row - j;
                    if (nextSlopeIndex < 0 || visited[nextSlopeIndex]) {
                        return false;
                    }
                    if (startPoint != maps[nextSlopeIndex][col]) { // 경사로의 높이가 달라지면 실패
                        return false;
                    }
                    visited[nextSlopeIndex] = true;
                }
            } else { // 내리막
                for (int j = 0; j < L; j++) {
                    int nextSlopeIndex = row + j;
                    if (nextSlopeIndex >= N || visited[nextSlopeIndex]) {
                        return false;
                    }
                    if (nextPoint != maps[nextSlopeIndex][col]) {
                        return false;
                    }
                    visited[nextSlopeIndex] = true;
                }
            }
        }
        return true;
    }
}
