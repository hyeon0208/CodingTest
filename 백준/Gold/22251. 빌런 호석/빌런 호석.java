import java.io.*;
import java.util.*;

public class Main {
    // 7-세그먼트 LED 배열 (0~9 숫자)
    static int[][] led = {
        {1,1,1,0,1,1,1}, // 0
        {0,0,1,0,0,1,0}, // 1
        {1,0,1,1,1,0,1}, // 2
        {1,0,1,1,0,1,1}, // 3
        {0,1,1,1,0,1,0}, // 4
        {1,1,0,1,0,1,1}, // 5
        {1,1,0,1,1,1,1}, // 6
        {1,0,1,0,0,1,0}, // 7
        {1,1,1,1,1,1,1}, // 8
        {1,1,1,1,0,1,1}  // 9
    };
    
    // 숫자 i를 j로 바꾸는데 필요한 LED 반전 개수
    static int[][] diff = new int[10][10];
    
    static void calcDiff() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                int count = 0;
                for(int k = 0; k < 7; k++) {
                    if(led[i][k] != led[j][k]) count++;
                }
                diff[i][j] = count;
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken()); // 최대 층수
        int K = Integer.parseInt(st.nextToken()); // 디스플레이 자릿수
        int P = Integer.parseInt(st.nextToken()); // 최대 반전 개수
        int X = Integer.parseInt(st.nextToken()); // 현재 층
        
        calcDiff(); // LED 반전 개수 전처리
        
        // X를 K자리 배열로 변환
        int[] current = new int[K];
        for(int i = K - 1; i >= 0; i--) {
            current[i] = X % 10;
            X /= 10;
        }
        
        int answer = 0;
        
        // 1층부터 N층까지 확인
        for(int floor = 1; floor <= N; floor++) {
            // floor를 K자리 배열로 변환
            int[] target = new int[K];
            int temp = floor;
            for(int i = K - 1; i >= 0; i--) {
                target[i] = temp % 10;
                temp /= 10;
            }
            
            // 현재 층과 비교하여 LED 반전 개수 계산
            int ledCount = 0;
            for(int i = 0; i < K; i++) {
                ledCount += diff[current[i]][target[i]];
            }
            
            // 1개 이상 P개 이하로 반전 가능하면 카운트
            if(ledCount >= 1 && ledCount <= P) {
                answer++;
            }
        }
        
        System.out.println(answer);
    }
}