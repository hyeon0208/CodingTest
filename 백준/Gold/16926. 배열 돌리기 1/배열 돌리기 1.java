import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int R;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                arr[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        turn();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    private static void turn() {
        int loops = Math.min(N, M) / 2; // 테두리 개수 공식

        for (int r = 0; r < R; r++) {
            // 각 테두리 회전
            for (int loop = 0; loop < loops; loop++) {
                int temp = arr[loop][loop];

                // 위: 오른쪽 -> 왼쪽
                for (int j = loop; j < M - 1 - loop; j++) {
                    arr[loop][j] = arr[loop][j + 1];
                }

                // 오른쪽: 아래 -> 위
                for (int i = loop; i < N - 1 - loop; i++) {
                    arr[i][M - 1 - loop] = arr[i + 1][M - 1 - loop];
                }

                // 아래: 왼쪽 -> 오른쪽
                for (int j = M - 1 - loop; j > loop; j--) {
                    arr[N - 1 - loop][j] = arr[N - 1 - loop][j - 1];
                }

                // 왼쪽: 위 -> 아래
                for (int i = N - 1 - loop; i > loop; i--) {
                    arr[i][loop] = arr[i - 1][loop];
                }

                // 저장해둔 값을 마지막 위치에 넣기
                arr[loop + 1][loop] = temp;
            }
        }
    }
}
