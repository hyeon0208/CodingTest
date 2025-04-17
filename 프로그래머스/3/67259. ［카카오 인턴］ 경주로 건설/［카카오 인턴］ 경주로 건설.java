import java.util.*;

class Solution {
    
    private static final int[] DX = {1, 0, -1, 0}; // 상, 좌, 하, 우
    private static final int[] DY = {0, -1, 0, 1};
    private static final int BLANK = 0;
    private static final int WALL = 1;
    private static final int STRAIGHT_PRICE = 100;
    private static final int CORNER_PRICE = 500;
    
    public int solution(int[][] board) {
        int n = board.length;
        
        int[][][] costCache = new int[n][n][4]; // (x,y)에 방향 dir로 도달하는 최소 비용을 저장
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(costCache[i][j], Integer.MAX_VALUE);
            }
        }
        Arrays.fill(costCache[0][0], 0); // 시작점을 0으로 초기화
        
        dfs(0, 0, -1, 0, board, costCache);
        
        int answer = Integer.MAX_VALUE;
        for (int k = 0; k < 4; k++) {
            answer = Math.min(answer, costCache[n - 1][n - 1][k]);
        }
        
        return answer;
    }
    
    private void dfs(int x, int y, int prevDir, int cost, int[][] board, int[][][] costCache) {
        int n = board.length;
        
        for (int dir = 0; dir < 4; dir++) {
            int nx = x + DX[dir];
            int ny = y + DY[dir];
            
            if (nx < 0 || nx >= n || ny < 0 || ny >= n || board[nx][ny] == WALL) {
                continue;
            }
            
            int newCost = cost + STRAIGHT_PRICE;
            if (prevDir != -1 && prevDir != dir) { // 방향 전환이 필요하면 코너 비용 추가
                newCost += CORNER_PRICE;
            }
            
            // 현재 방향으로 이동했을 때 비용이 기존 비용보다 작은 경우만 진행
            if (newCost < costCache[nx][ny][dir]) {
                costCache[nx][ny][dir] = newCost;
                dfs(nx, ny, dir, newCost, board, costCache);
            }
        }
    }
}