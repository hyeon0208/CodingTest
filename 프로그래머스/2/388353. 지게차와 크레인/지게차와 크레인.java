import java.util.*;

class Solution {
    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};

    public int solution(String[] storage, String[] requests) {
        char[][] containers = new char[storage.length][];

        for (int i = 0; i < storage.length; i++) {
            containers[i] = storage[i].toCharArray();
        }

        for (String request : requests) {
            char c = request.charAt(0);

            if (request.length() == 1) {
                removeWithForklift(containers, c);
            } else {
                removeWithCrane(containers, c);
            }

        }

        int count = 0;

        for (char[] container : containers) {
            for (char c : container) {
                if (c != 0) {
                    count++;
                }
            }
        }
        return count;
    }

    private void removeWithCrane(char[][] containers, char c) {
        for (int i = 0; i < containers.length; i++) {
            for (int j = 0; j < containers[i].length; j++) {
                if (containers[i][j] == c) {
                    containers[i][j] = 0;
                }
            }
        }
    }

    private void removeWithForklift(char[][] containers, char c) {
        boolean[][] isVisited = new boolean[containers.length][containers[0].length];

        for (int i = 0; i < containers.length; i++) {
            for (int j = 0; j < containers[i].length; j++) {
                if ((i == 0 || j == 0 || i == containers.length - 1 || j == containers[i].length - 1)
                        && !isVisited[i][j]) {
                    dfs(containers, i, j, c, isVisited);
                }
            }
        }

        for (int i = 0; i < containers.length; i++) {
            for (int j = 0; j < containers[i].length; j++) {
                if (containers[i][j] == 1) {
                    containers[i][j] = 0;
                }
            }
        }
    }

    private void dfs(char[][] containers, int x, int y, char c, boolean[][] isVisited) {
        if (x < 0 || y < 0 || x >= containers.length || y >= containers[x].length 
                || isVisited[x][y]) {
            return;
        }

        isVisited[x][y] = true;

        if (containers[x][y] == c) {
            containers[x][y] = 1;
            return;
        }

        if(containers[x][y] != 0) return;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];
            dfs(containers, nx, ny, c, isVisited);
        }
    }
}