import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static boolean[][] visited;
    static int w;
    static int h;
    static Position start;
    static Position goal;
    
    public int solution(String[] board) {
        w = board.length;
        h = board[0].length();
        visited = new boolean[w][h];
        
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                if (board[i].charAt(j) == 'R') {
                    start = new Position(i, j, 0);
                }
                if (board[i].charAt(j) == 'G') {
                    goal = new Position(i, j, 0);
                }
            }
        }
        return bfs(board);
    }
    
    private int bfs(String[] board) {
        Queue<Position> q = new LinkedList<>();
        q.add(start);
        visited[start.x][start.y] = true;
        
        while (!q.isEmpty()) {
            Position cur = q.poll();
            if (cur.x == goal.x && cur.y == goal.y) {
                return cur.count;
            }
                                
            for (int i = 0; i < 4; i++) {
                int nextX = dx[i] + cur.x;
                int nextY = dy[i] + cur.y;
                
                while (nextX >= 0 && nextY >= 0 && nextX < w && nextY < h && board[nextX].charAt(nextY) != 'D') {
                    nextX += dx[i];
                    nextY += dy[i];
                }
                nextX -= dx[i];
                nextY -= dy[i];

                if (!visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    q.add(new Position(nextX, nextY, cur.count +1));
                }
            }
        }
        return -1;
    }
    
    
    static class Position {
        int x;
        int y;
        int count;
        
        public Position(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
}