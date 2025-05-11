class Solution {
    
    private static int N;
    private static int M;
    private static boolean[] visitedRow;
    private static boolean[] visitedCol;

    private static int result = Integer.MAX_VALUE;
    
    public static int solution(int[][] beginning, int[][] target) {
        N = beginning.length;
        M = beginning[0].length;
        visitedRow = new boolean[N];
        visitedCol = new boolean[M];
        dfs(beginning, target, visitedRow, visitedCol, 0, 0);

        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private static void dfs(int[][] beginning, int[][] target, boolean[] visitedRow, boolean[] visitedCol, int depth, int count) {
        int[][] copyArr = copyArr(beginning);
        applyFlips(copyArr, visitedRow, visitedCol);
        if (match(copyArr, target)) {
            result = Math.min(result, count);
            return;
        }
        if (depth == N + M) {
            return;
        }
        if (depth < N) { // 행 탐색
            dfs(beginning, target, visitedRow, visitedCol, depth + 1, count); // 현재 행을 뒤집지 않는 경우

            visitedRow[depth] = true;
            dfs(beginning, target, visitedRow, visitedCol, depth + 1, count + 1);
            visitedRow[depth] = false;
        } else { // 열 탐색
            dfs(beginning, target, visitedRow, visitedCol, depth + 1, count); // 현재 열을 뒤집지 않는 경우

            visitedCol[depth - N] = true;
            dfs(beginning, target, visitedRow, visitedCol, depth + 1, count + 1);
            visitedCol[depth - N] = false;
        }
    }


    private static boolean match(int[][] beginning, int[][] target) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (beginning[i][j] != target[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int[][] copyArr(int[][] beginning) {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            copy[i] = beginning[i].clone();
        }
        return copy;
    }

    private static void applyFlips(int[][] arr, boolean[] flipRows, boolean[] flipCols) {
        // 행 뒤집기
        for (int i = 0; i < N; i++) {
            if (flipRows[i]) {
                for (int j = 0; j < M; j++) {
                    arr[i][j] = 1 - arr[i][j];
                }
            }
        }

        // 열 뒤집기
        for (int j = 0; j < M; j++) {
            if (flipCols[j]) {
                for (int i = 0; i < N; i++) {
                    arr[i][j] = 1 - arr[i][j];
                }
            }
        }
    }
}