import java.util.*;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        
        Arrays.sort(data, Comparator.comparingInt((int[] arr) -> arr[col - 1])
                    .thenComparingInt(arr -> -arr[0]));
        
         for (int i = row_begin - 1; i < row_end; i++) {
            int mod = i + 1;
            int sum = Arrays.stream(data[i])
                    .map(value -> value % mod)
                    .sum();
             
            answer = (answer ^ sum);
        }
        
        return answer;
    }
}