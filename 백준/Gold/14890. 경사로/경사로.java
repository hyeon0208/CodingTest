import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        
        // 맵 입력
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int answer = 0;
        
        // 가로 방향 확인
        for(int i = 0; i < N; i++) {
            if(checkRow(i)) answer++;
        }
        
        // 세로 방향 확인
        for(int j = 0; j < N; j++) {
            if(checkCol(j)) answer++;
        }
        
        System.out.println(answer);
    }
    
    // 가로 방향 확인
    static boolean checkRow(int row) {
        boolean[] visited = new boolean[N]; // 경사로 설치 여부 체크
        
        for(int i = 1; i < N; i++) {
            int diff = map[row][i] - map[row][i-1];
            
            if(diff == 0) continue; // 높이가 같으면 패스
            if(Math.abs(diff) > 1) return false; // 높이 차이가 1보다 크면 불가능
            
            if(diff == 1) { // 오르막
                for(int j = 1; j <= L; j++) {
                    if(i-j < 0 || visited[i-j]) return false;
                    if(map[row][i-1] != map[row][i-j]) return false;
                    visited[i-j] = true;
                }
            } else { // 내리막
                for(int j = 0; j < L; j++) {
                    if(i+j >= N || visited[i+j]) return false;
                    if(map[row][i] != map[row][i+j]) return false;
                    visited[i+j] = true;
                }
            }
        }
        return true;
    }
    
    // 세로 방향 확인
    static boolean checkCol(int col) {
        boolean[] visited = new boolean[N]; // 경사로 설치 여부 체크
        
        for(int i = 1; i < N; i++) {
            int diff = map[i][col] - map[i-1][col];
            
            if(diff == 0) continue;
            if(Math.abs(diff) > 1) return false;
            
            if(diff == 1) { // 오르막
                for(int j = 1; j <= L; j++) {
                    if(i-j < 0 || visited[i-j]) return false;
                    if(map[i-1][col] != map[i-j][col]) return false;
                    visited[i-j] = true;
                }
            } else { // 내리막
                for(int j = 0; j < L; j++) {
                    if(i+j >= N || visited[i+j]) return false;
                    if(map[i][col] != map[i+j][col]) return false;
                    visited[i+j] = true;
                }
            }
        }
        return true;
    }
}