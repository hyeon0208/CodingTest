import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int H;
    private static boolean[][] ladder;
    private static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 세로
        M = Integer.parseInt(st.nextToken()); // 가로
        H = Integer.parseInt(st.nextToken()); // 가로선을 놓을 수 있는 위치 수

        ladder = new boolean[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            ladder[a][b] = true;
        }

        // 0개부터 3개까지 가로선을 추가하는 모든 경우를 시도
        for (int i = 0; i <= 3; i++) {
            dfs(1, 1, 0, i);
            if (result != -1) {
                break;
            }
        }

        System.out.println(result);
    }

    // row: 현재 행, col: 현재 열, count: 현재까지 놓은 가로선 수, target: 목표 가로선 수
    private static void dfs(int row, int col, int count, int target) {
        if (result != -1) {
            return;  // 이미 답을 찾은 경우
        }

        if (count == target) {  // 목표한 개수만큼 가로선을 놓은 경우
            if (check()) {
                result = target;
            }
            return;
        }

        for (int i = row; i <= H; i++) {
            for (int j = (i == row ? col : 1); j < N; j++) {
                // 현재 위치와 양옆에 가로선이 없는 경우에만 설치 가능
                if (!ladder[i][j] && !ladder[i][j - 1] && !ladder[i][j + 1]) {
                    ladder[i][j] = true;
                    dfs(i, j + 2, count + 1, target); // 연속된 가로 선을 방지하기 위해 세로 선 + 2
                    ladder[i][j] = false;
                }
            }
        }
    }

    // 모든 세로선이 자기 자신으로 도착하는지 확인
    private static boolean check() {
        for (int start = 1; start <= N; start++) {
            int cur = start;
            for (int i = 1; i <= H; i++) {
                if (ladder[i][cur - 1]) {
                    cur--;
                } else if (ladder[i][cur]) {
                    cur++;
                }
            }
            if (cur != start) {
                return false;
            }
        }
        return true;
    }
}
