import java.util.*;

class Solution {
    
    // 아래, 오른쪽, 대각선 위
    private static final int[] dx = {1, 0, -1};  
    private static final int[] dy = {0, 1, -1};  
    
    public int[] solution(int n) {
        int[][] triangle = new int[n][n];
        int value = 1;
        int x = 0, y = 0;
        int direction = 0;
        
        int totalSize = n * (n + 1) / 2;
        
        while (value <= totalSize) {
            // 현재 위치에 값 채우기
            triangle[x][y] = value++;
            
            // 다음 위치 계산
            int nextX = x + dx[direction];
            int nextY = y + dy[direction];
            
            // 다음 위치가 유효하지 않거나 이미 값이 있으면 방향 전환
            if (!isValid(nextX, nextY, n) || triangle[nextX][nextY] != 0) {
                direction = (direction + 1) % 3;
                nextX = x + dx[direction];
                nextY = y + dy[direction];
            }
            
            x = nextX;
            y = nextY;
        }
        
        return convertToArray(triangle, totalSize);
    }
    
    // 좌표가 유효한지 검사
    private boolean isValid(int x, int y, int n) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
    
    // 2차원 배열을 1차원 배열로 변환
    private int[] convertToArray(int[][] triangle, int totalSize) {
        int[] result = new int[totalSize];
        int index = 0;
        
        for (int i = 0; i < triangle.length; i++) {
            for (int j = 0; j <= i; j++) {
                result[index++] = triangle[i][j];
            }
        }
        
        return result;
    }
}