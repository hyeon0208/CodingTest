import java.util.*;

class Solution {
    private static char[][] map;
    private static boolean[][] isSame;
    private static int result = 0;

    public static int solution(int m, int n, String[] board) {

        map = new char[m][n];
        isSame = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            String line = board[i];
            for (int j = 0; j < n; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        while (checkBlock()) {
            pop();
            dropBlock();
            for (int i = 0; i < isSame.length; i++) {
                Arrays.fill(isSame[i], false);
            }
        }

        return result;
    }

    private static boolean checkBlock() {
        boolean canPop = false;
        for (int i = 0; i < map.length - 1; i++) {
            for (int j = 0; j < map[i].length - 1; j++) {
                char c = map[i][j];
                char right = map[i][j + 1];
                char bottom = map[i + 1][j];
                char cross = map[i + 1][j + 1];
                if (c != '-' && c == right && c == bottom && c == cross) {
                    isSame[i][j] = true;
                    isSame[i][j + 1] = true;
                    isSame[i + 1][j] = true;
                    isSame[i + 1][j + 1] = true;
                    canPop = true;
                }
            }
        }
        return canPop;
    }

    private static void pop() {
        for (int i = 0; i < isSame.length; i++) {
            for (int j = 0; j < isSame[i].length; j++) {
                if (isSame[i][j]) {
                    map[i][j] = '-';
                    result++;
                }
            }
        }
    }

    private static void dropBlock() {
        for (int col = 0; col < map[0].length; col++) {
            boolean moved = true;
            while (moved) {
                moved = false;
                for (int row = map.length - 2; row >= 0; row--) {
                    if (map[row][col] != '-' && map[row + 1][col] == '-') {
                        map[row + 1][col] = map[row][col];
                        map[row][col] = '-';
                        moved = true;
                    }
                }
            }
        }
    }
}