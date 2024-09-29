class Solution {
    public long solution(int n) {        
        long[] pibo = new long[100_001];
        pibo[0] = 0;
        pibo[1] = 1;
        
        for(int i = 2; i <= n ; i++) { 
            pibo[i] = (pibo[i-1] + pibo[i-2]) % 1234567L;
        } 
        
        return pibo[n];
    }
}