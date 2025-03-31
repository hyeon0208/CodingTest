class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int position = 1; 

        for (int station : stations) {
            int uncovered = (station - w) - position;

            if (uncovered > 0) {
                answer += Math.ceil((double) uncovered / (2 * w + 1));
            }
            position = station + w + 1;
        }
        
        if (position <= n) {
            int uncovered = n - position + 1;
            System.out.println(uncovered);
            answer += Math.ceil((double) uncovered / (2 * w + 1));
        }

        return answer;
    }
}