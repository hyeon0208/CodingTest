import java.util.*;

class Solution {
    private static int[] networks;
     
    public int solution(int n, int[][] computers) {
        int result = 0;
        
        networks = new int[n];
        for (int i = 0; i < n; i++) {
            networks[i] = i;
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (computers[i][j] == 1) {
                    union(i, j);
                }
            }
        }
        
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i));
        }
        
        return set.size();
    }
    
    private int find(int x) {
        if (networks[x] == x) {
            return x;
        }
        return networks[x] = find(networks[x]);
    }
    
    private void union(int x, int y) {
        int network1 = find(x);
        int network2 = find(y);
        
        if (network1 == network2) {
            return;
        }
        
        if (network1 < network2) {
            networks[network2] = network1;
        } else {
            networks[network1] = network2;
        }
    }
}