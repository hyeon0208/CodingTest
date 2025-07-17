import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;


        Arrays.sort(citations, Comparator.reverseOrder());

        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > answer) {
                answer++;
                continue;
            }
            if (citations[i] <= answer) {
                return answer;
            }
        }

        return answer;
    }
}