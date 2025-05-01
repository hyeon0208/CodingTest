import java.util.*;

class Solution {
    
    private static boolean[][] pillar;
    private static boolean[][] bar;
    
    public static int[][] solution(int n, int[][] build_frame) {
        pillar = new boolean[n + 1][n + 1];
        bar = new boolean[n + 1][n + 1];

        int count = 0;
        for (int[] frame : build_frame) {
            int x = frame[0];
            int y = frame[1];
            int type = frame[2];
            int action = frame[3];

            if (type == 0) {
                if (action == 1) {
                    if (buildPillar(x, y)) {
                        pillar[x][y] = true;
                        count++;
                    }
                } else {
                    pillar[x][y] = false;
                    if (!canDelete(n)) {
                        pillar[x][y] = true;
                    } else {
                        count--;
                    }
                }
            } else {
                if (action == 1) {
                    if (buildBar(x, y)) {
                        bar[x][y] = true;
                        count++;
                    }
                } else {
                    bar[x][y] = false;
                    if (!canDelete(n)) {
                        bar[x][y] = true;
                    } else {
                        count--;
                    }
                }
            }
        }

        int[][] result = new int[count][3];
        int idx = 0;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillar[i][j]) {
                    result[idx][0] = i;
                    result[idx][1] = j;
                    result[idx][2] = 0;
                    idx++;
                }
                if (bar[i][j]) {
                    result[idx][0] = i;
                    result[idx][1] = j;
                    result[idx][2] = 1;
                    idx++;
                }
            }
        }
        return result;
    }

    private static boolean canDelete(int n) {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (pillar[i][j] && !buildPillar(i, j)) {
                    return false;
                }
                if (bar[i][j] && !buildBar(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean buildPillar(int x, int y) {
        if (y == 0) {
            return true;
        }
        if (y > 0 && pillar[x][y - 1]) {
            return true;
        }
        if (x > 0 && bar[x - 1][y] || bar[x][y]) {
            return true;
        }
        return false;
    }

    private static boolean buildBar(int x, int y) {
        if (y > 0 && pillar[x][y - 1] || pillar[x + 1][y - 1]) {
            return true;
        }
        if (x > 0 && bar[x - 1][y] && bar[x + 1][y]) {
            return true;
        }
        return false;
    }
}