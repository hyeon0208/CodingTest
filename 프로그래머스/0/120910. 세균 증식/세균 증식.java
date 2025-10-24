class Solution {
    public int solution(int n, int t) {
        int answer = n;
        
        for (int hour = 1; hour <= t; hour++) {
            answer = answer * 2;
        }
        
        return answer;
    }
}