import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int MOD = 1000;

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken()) % MOD;
            }
        }

        int[][] result = matrixPower(matrix, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static int[][] matrixPower(int[][] matrix, long B) {
        if (B == 1) { // A^1 = A
            return matrix;
        }

        if (B % 2 == 0) { // 짝수인 경우: A^B = (A^(B/2))^2
            int[][] half = matrixPower(matrix, B / 2);
            return multiplyMatrix(half, half);
        } else { // 홀수인 경우: A^B = A × A^(B-1)
            return multiplyMatrix(matrix, matrixPower(matrix, B - 1));
        }
    }

    private static int[][] multiplyMatrix(int[][] arr1, int[][] arr2) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] = (result[i][j] + arr1[i][k] * arr2[k][j]) % MOD;
                }
            }
        }

        return result;
    }
}
