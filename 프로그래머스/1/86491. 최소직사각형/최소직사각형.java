class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int wMax = 0;
        int hMax = 0;

        for (int i = 0; i < sizes.length; i++) {
            int width = Math.max(sizes[i][0], sizes[i][1]);
            if (sizes[i][0] < sizes[i][1]) {
                sizes[i][1] = sizes[i][0];
                sizes[i][0] = width;
            }
            
            wMax = Math.max(wMax, sizes[i][0]);
            hMax = Math.max(hMax, sizes[i][1]);
        }
        answer = wMax * hMax;

        return answer;
    }
}