import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    
    public static int[] solution(int m, int n, int[][] picture) {
        int maxArea = 0;
        int[] DX = {1, -1, 0, 0};
        int[] DY = {0, 0, 1, -1};
        boolean[][] visited = new boolean[m][n];
        int areaCount = 0;
        for (int i = 0; i < picture.length; i++) {
            for (int j = 0; j < picture[i].length; j++) {
                if (!visited[i][j] && picture[i][j] != 0) {
                    maxArea = Math.max(maxArea, bfs(i, j, picture, visited, DX, DY));
                    areaCount++;
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = areaCount;
        answer[1] = maxArea;
        return answer;
    }

    private static int bfs(int x, int y, int[][] picture, boolean[][] visited, int[] DX, int[] DY) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(x, y));
        visited[x][y] = true;

        int areaSum = 1;

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + DX[i];
                int nextY = cur.y + DY[i];

                if (0 <= nextX && nextX < picture.length && 0 <= nextY && nextY < picture[i].length) {
                    if (!visited[nextX][nextY] && picture[cur.x][cur.y] == picture[nextX][nextY]) {
                        queue.offer(new Node(nextX, nextY));
                        visited[nextX][nextY] = true;
                        areaSum++;
                    }
                }
            }
        }
        return areaSum;
    }

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}