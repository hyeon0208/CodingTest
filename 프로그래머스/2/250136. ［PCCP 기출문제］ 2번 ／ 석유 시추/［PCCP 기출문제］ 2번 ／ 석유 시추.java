import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int width;
    static int height;
    static Map<Integer, Integer> oilResut;
    static int answer = 0;
    static int[] oilSum;
    static int[][] oliWidthPosition;

    public int solution(int[][] land) {
        int answer = 0;
        width = land.length;
        height = land[0].length;
        visited = new boolean[width][height];
        oliWidthPosition = new int[width][height];
        oilResut = new HashMap<>();
        
        int label = 10;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    int oliSize = bfs(i, j, land, label);
                    oilResut.put(label, oliSize);
                    label++;
                }
            }
        }
        
        oilSum = new int[height];
        for (int j = 0; j < height; j++) {  
            Set<Integer> oilSet = new HashSet<>();  
            for (int i = 0; i < width; i++) {  
                if (land[i][j] == 1) {  
                    oilSet.add(oliWidthPosition[i][j]);  
                }  
            }  
            for (int id : oilSet) {  
                oilSum[j] += oilResut.get(id);  
            }  
        }

        return Arrays.stream(oilSum).max().getAsInt();  
    }
    
    private int bfs(int x, int y, int[][] land, int label) {
        int oliSize = 1;
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x, y));
        visited[x][y] = true;
        oliWidthPosition[x][y] = label;
        
        while (!q.isEmpty()) {
            Position cur = q.poll();
            
            for (int i = 0; i < 4; i++) {
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                
                if (0 <= nextX && nextX < width && 0 <= nextY && nextY < height && !visited[nextX][nextY] && land[nextX][nextY] != 0) {
                    oliSize++;
                    visited[nextX][nextY] = true;
                    q.offer(new Position(nextX, nextY));
                    oliWidthPosition[nextX][nextY] = label;
                }
            }
        }
        return oliSize;
    }
    
    class Position {
        int x;
        int y;
        
        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}