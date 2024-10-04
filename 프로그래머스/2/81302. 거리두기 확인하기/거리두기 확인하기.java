import java.util.*;

class Solution {
    // 상하좌우 이동을 위한 배열
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        for (int i = 0; i < places.length; i++) {
            answer[i] = isValidPlace(places[i]) ? 1 : 0;
        }
        
        return answer;
    }
    
    private boolean isValidPlace(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!checkDistance(place, i, j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean checkDistance(String[] place, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                if (place[nx].charAt(ny) == 'P') {
                    return false;
                } else if (place[nx].charAt(ny) == 'O') {
                    if (checkAdjacentPerson(place, nx, ny, x, y)) {
                        return false;
                    }
                }
            }
        }
        
        // 대각선 확인
        int[] diagonalX = {-1, -1, 1, 1};
        int[] diagonalY = {-1, 1, -1, 1};
        
        for (int i = 0; i < 4; i++) {
            int nx = x + diagonalX[i];
            int ny = y + diagonalY[i];
            
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                if (place[nx].charAt(ny) == 'P') {
                    if (place[x].charAt(ny) != 'X' || place[nx].charAt(y) != 'X') {
                        return false;
                    }
                }
            }
        }
        
        return true;
    }
    
    private boolean checkAdjacentPerson(String[] place, int x, int y, int prevX, int prevY) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < 5 && ny >= 0 && ny < 5) {
                if (nx == prevX && ny == prevY) continue;
                if (place[nx].charAt(ny) == 'P') {
                    return true;
                }
            }
        }
        return false;
    }
}