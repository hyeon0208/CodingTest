import java.util.*;

class Solution {
    
    private static int[] parent;
    
    public int solution(int n, int[][] computers) {        
        parent = new int[n];
        
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        for (int x = 0; x < computers.length; x++) {
            for (int y = 0; y < computers[x].length; y++) {
                if (computers[x][y] == 1) {
                    union(x, y);
                }
            }
        }
        
        
        Set<Integer> set = new HashSet();
        for (int i = 0; i < n; i++) {
            set.add(find(parent[i]));
        }
         
        return set.size();
    }
    
    private void union(int a, int b) {
        int aParent = find(a);
        int bParent = find(b);
        
        if (aParent == bParent) {
            return;
        }
        
        if (aParent < bParent) {
            parent[bParent] = aParent;
        } else {
            parent[aParent] = bParent;
        }
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
 
} 