import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        List<Integer> answer = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            int[] subArr = Arrays.copyOfRange(array, commands[i][0] - 1, commands[i][1]);
            Arrays.sort(subArr);
            answer.add(subArr[commands[i][2] - 1]);
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }
}