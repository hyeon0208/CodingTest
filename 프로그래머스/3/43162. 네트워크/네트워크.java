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
        
        // parent 배열: 직속 부모만 저장 (1단계만 올라감)
        // find(): 최종 루트까지 올라감 (꼭대기까지!) 
        // 즉 최종 루트를 찾기 위해 find()를 마지막에 한번더 
        Set<Integer> set = new HashSet();
        for (int i = 0; i < n; i++) {
            set.add(find(parent[i]));
        }

        System.out.println(Arrays.toString(parent));

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