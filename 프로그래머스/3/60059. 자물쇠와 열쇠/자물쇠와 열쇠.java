class Solution {
    
    private static final int STICK = 1;
    
    public static boolean solution(int[][] key, int[][] lock) {
        int n = key.length;
        int m = lock.length;
        int boardSize = (n * 2) + m - 2;
        int[][] board = new int[boardSize][boardSize];

        for (int i = n - 1; i < n + m - 1; i++) {
            for (int j = n - 1; j < n + m - 1; j++) {
                board[i][j] = lock[i - n + 1][j - n + 1];
            }
        }

        for (int i = 0; i <= 4; i++) {
            if (isMatch(key, board)) {
                return true;
            }
            rotate(key);
        }

        return false;
    }

    private static boolean isMatch(int[][] key, int[][] board) {
        for (int i = 0; i < board.length - key.length + 1; i++) {
            for (int j = 0; j < board.length - key.length + 1; j++) {
                int[][] tempBoard = new int[board.length][board.length];
                for (int k = 0; k < board.length; k++) {
                    tempBoard[k] = board[k].clone();
                }
                for (int x = 0; x < key.length; x++) {
                    for (int y = 0; y < key.length; y++) {
                        tempBoard[x + i][y + j] += key[x][y];
                    }
                }
                if (checkBoard(tempBoard, key.length)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkBoard(int[][] tempBoard, int keyLen) {
        for (int x = keyLen - 1; x < tempBoard.length - keyLen + 1; x++) {
            for (int y = keyLen - 1; y < tempBoard.length - keyLen + 1; y++) {
                if (tempBoard[x][y] != STICK) {
                    return false;
                }
            }
        }
        return true;
    }

    // 시계 방향으로 90도 회전
    private static void rotate(int[][] key) {
        int len = key.length;
        int[][] temp = new int[len][len];

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                temp[j][len - i - 1] = key[i][j];
            }
        }

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                key[i][j] = temp[i][j];
            }
        }
    }
}