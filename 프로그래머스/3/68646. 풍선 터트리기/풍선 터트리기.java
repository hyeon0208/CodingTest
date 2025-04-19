class Solution {
    public int solution(int[] a) {
        int n = a.length;
        
        int[] leftMin = new int[n];
        int[] rightMin = new int[n];
        
        // 왼쪽 최솟값 계산
        leftMin[0] = a[0];
        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }
        
        // 오른쪽 최솟값 계산
        rightMin[n-1] = a[n-1];
        for (int i = n-2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (a[i] == leftMin[i] || a[i] == rightMin[i]) {
                count++;
            }
        }
        
        return count;
    }
}