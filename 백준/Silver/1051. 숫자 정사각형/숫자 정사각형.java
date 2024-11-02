import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[][] rectangle;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N == 1 || M == 1) {
            System.out.println(1);
            return;
        }

        rectangle = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                rectangle[i][j] = line.charAt(j) - '0';
            }
        }

        int maxSize = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int maxPossibleSize = Math.min(N - i, M - j);
                for (int len = 1; len <= maxPossibleSize; len++) {
                    if (isValidSquare(i, j, len - 1)) {
                        maxSize = Math.max(maxSize, len);
                    }
                }
            }
        }

        System.out.println(maxSize * maxSize);
    }

    private static boolean isValidSquare(int row, int col, int size) {
        // 경계 체크 추가
        if (row + size > N || col + size > M) {
            return false;
        }

        int number = rectangle[row][col];  // 좌측 상단 숫자
        return number == rectangle[row][col + size] &&      // 우측 상단
                number == rectangle[row + size][col] &&      // 좌측 하단
                number == rectangle[row + size][col + size]; // 우측 하단
    }
}
