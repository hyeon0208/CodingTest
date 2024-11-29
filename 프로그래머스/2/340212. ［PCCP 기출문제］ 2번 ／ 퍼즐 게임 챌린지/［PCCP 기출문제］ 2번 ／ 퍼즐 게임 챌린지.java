import java.util.*;

class Solution {
    
    public int solution(int[] diffs, int[] times, long limit) {
        return binarySearch(diffs, times, limit);
    }
    
    public int binarySearch(int [] diffs, int[] times, long limit) {
        int min = 1; 
        int max = 100000; 
        
        while (min <= max){
            int level = (min + max) / 2; 
            long mid = calculate(diffs, times, level);
            if(mid > limit) {
                min = level + 1;
            } else {
                max = level - 1; 
            }
        }
        return min;
    }

    public long calculate(int [] diffs, int[] times, int level) {
        long totalTime = 0;
        for(int i = 0; i < diffs.length; i++){
            if(diffs[i] <= level) {
                totalTime += times[i];
            } else {
                totalTime += (long) (times[i] + times[i - 1]) * (long) (diffs[i] - level) + times[i];
            }
        }
        return totalTime;
    }
}
