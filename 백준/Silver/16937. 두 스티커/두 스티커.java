import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(br.readLine());
        
        int[][] stickers = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            stickers[i][0] = Integer.parseInt(st.nextToken());
            stickers[i][1] = Integer.parseInt(st.nextToken());
        }
        
        int maxArea = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int[][] orientations1 = {{stickers[i][0], stickers[i][1]}, {stickers[i][1], stickers[i][0]}};
                int[][] orientations2 = {{stickers[j][0], stickers[j][1]}, {stickers[j][1], stickers[j][0]}};
                
                for (int[] ori1 : orientations1) {
                    for (int[] ori2 : orientations2) {
                        int r1 = ori1[0], c1 = ori1[1];
                        int r2 = ori2[0], c2 = ori2[1];
                        
                        // 가로 배치: 두 스티커를 가로로 나란히
                        if (r1 <= H && r2 <= H && c1 + c2 <= W) {
                            maxArea = Math.max(maxArea, r1 * c1 + r2 * c2);
                        }
                        
                        // 세로 배치: 두 스티커를 세로로 나란히
                        if (r1 + r2 <= H && c1 <= W && c2 <= W) {
                            maxArea = Math.max(maxArea, r1 * c1 + r2 * c2);
                        }
                    }
                }
            }
        }
        
        System.out.println(maxArea);
    }
}