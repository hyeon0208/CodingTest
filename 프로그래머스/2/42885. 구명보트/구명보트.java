import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        Arrays.sort(people);
        int start = 0;
        int end = people.length - 1;

        while (start <= end) {
            int weight = people[start] + people[end];

            if (weight <= limit) {
                start++;
                end--;
                answer++;
            }

            if (weight > limit) {
                end--;
                answer++;
            }
        }
        return answer;
    }
}