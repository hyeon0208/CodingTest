class Solution {

    private static final int ATTACK = 1;
    
    public static int solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;

        int[][] prefixSum = new int[n + 1][m + 1];

        for (int[] s : skill) {
            int type = s[0];
            int degree = s[5];

            if (type == ATTACK) {
                degree *= -1;
            }
            int r1 = s[1];
            int c1 = s[2];
            int r2 = s[3];
            int c2 = s[4];

            prefixSum[r1][c1] += degree;        // 왼쪽 상단
            prefixSum[r2 + 1][c2 + 1] += degree;    // 오른쪽 하단
            prefixSum[r1][c2 + 1] -= degree;      // 오른쪽 상단
            prefixSum[r2 + 1][c1] -= degree;      // 왼쪽 하단
        }

        // 행 방향으로 누적합 계산
        for (int i = 0; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                prefixSum[i][j] += prefixSum[i][j - 1];
            }
        }

        // 열 방향으로 누적합 계산
        for (int j = 0; j < m + 1; j++) {
            for (int i = 1; i < n + 1; i++) {
                prefixSum[i][j] += prefixSum[i - 1][j];
            }
        }

        return getBuildingCount(board, prefixSum);
    }

    private static int getBuildingCount(int[][] board, int[][] prefixSum) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + prefixSum[i][j] > 0) {
                    count++;
                }
            }
        }
        return count;
    }
}