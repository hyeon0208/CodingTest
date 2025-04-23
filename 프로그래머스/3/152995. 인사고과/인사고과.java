import java.util.*;

class Solution {
    
    public static int solution(int[][] scores) {
        int[] wanho = scores[0];
        int rank = 1;
        int prevScore = 0;
        int wanhoTotal = wanho[0] + wanho[1];

        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        for (int[] score : scores) {
            if (prevScore <= score[1]) {
                prevScore = score[1];
                if (score[0] + score[1] > wanhoTotal) {
                    rank++;
                }
            } else {
                if (score.equals(wanho)) {
                    return -1;
                }
            }
        }

        return rank;
    }
}