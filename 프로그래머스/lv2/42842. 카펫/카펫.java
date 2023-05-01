class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        int carpet = brown + yellow;
        // 세로의 크기는 3이거나 3보다 커야 노란 격자가 존재할 수 있다.
        for (int i = 3; i < carpet; i++) {
            int col = i;
            int row = carpet / col;

            // 가로의 크기도 3이거나 3보다 커야 노란 격자가 존재할 수 있다.
            if (row < 3) {
                continue;
            }

            if (row >= col) {
                // 가로, 세로에서 겹치는 부분을 제외하여 곱한 값 = yellow의 넓이
                if ((row - 2) * (col - 2) == yellow) {
                    answer[0] = row;
                    answer[1] = col;
                    break;
                }
            }
        }

        return answer;
    }
}