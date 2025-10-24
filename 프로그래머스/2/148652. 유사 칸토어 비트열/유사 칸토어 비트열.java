class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        for (long i = l; i <= r; i++) {
            if (isOne(n, i - 1)) {
                answer++;
            }
        }
        return answer;
    }
    
    private boolean isOne(int n, long index) {
        if (n == 0) return true;
        
        long length = (long) Math.pow(5, n);
        long section = length / 5;
        int pos = (int) (index / section);
        
        if (pos == 2) return false; // 가운데 구역은 모두 0
        
        return isOne(n - 1, index % section);
    }
}