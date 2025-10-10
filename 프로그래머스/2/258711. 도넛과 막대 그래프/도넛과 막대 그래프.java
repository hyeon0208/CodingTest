import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] result = new int[4];
        int N = 0;
        
        // 정점 개수를 찾음
        for(int i = 0; i < edges.length; i++) {
            N = Math.max(N, Math.max(edges[i][0], edges[i][1]));
        }
        
        int[] in = new int[N + 1];
        int[] out = new int[N + 1];
        
        for (int i = 0; i < edges.length; i++) {
            in[edges[i][1]]++;
            out[edges[i][0]]++;
        }
        
        for (int i = 1; i <= N; i++) {
            if (out[i] - in[i] > 1) {
                result[0] = i;
                break;
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (out[i] >= 2 && in[i] >= 2) { // 8자 모양
                result[3]++;
            }
            if (out[i] == 0 && in[i] > 0) {
                result[2]++;
            }  
        }
        
        result[1] = out[result[0]] - result[2] - result[3];
        
        return result;
    }
}