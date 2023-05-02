import java.util.Arrays;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Arrays.sort(people);

        int start = 0;
        int end = people.length - 1;

        while (start <= end) {
            int weight = people[start] + people[end];
            
            // 몸무게 제한을 넘지 않으면 두명을 한배에 태워 보낸다.
            if (weight <= limit) {
                start++;
                end--;
                answer++;
            }

            // 몸무게 제한을 넘으면 몸무게가 무거운 사람을 보트에 혼자 태워 보낸다.
            if (weight > limit) {
                end--;
                answer++;
            }
        }
        return answer;
    }
}