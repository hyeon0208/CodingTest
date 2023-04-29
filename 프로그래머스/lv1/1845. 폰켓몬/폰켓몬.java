import java.util.*;

class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        int pickCount = nums.length / 2;

        Set<Integer> set = new HashSet<>();
        Arrays.stream(nums).forEach(n -> set.add(n));
        
        answer = Math.min(set.size(), pickCount);

        return answer;
    }
}