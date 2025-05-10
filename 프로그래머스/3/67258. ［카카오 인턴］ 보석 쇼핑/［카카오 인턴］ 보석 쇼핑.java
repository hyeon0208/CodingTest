import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] solution(String[] gems) {
        Set<String> uniqueGems = new HashSet<>(Arrays.asList(gems));
        int totalKinds = uniqueGems.size();

        if (totalKinds == 1) {
            return new int[]{1, 1};
        }

        int start = 0;
        int end = 0;
        int minLength = gems.length;
        int resultStart = 0;
        Map<String, Integer> gemCount = new HashMap<>();
        boolean found = false;
        while (end < gems.length) {
            String currentGem = gems[end];
            gemCount.put(currentGem, gemCount.getOrDefault(currentGem, 0) + 1);
            if (gemCount.size() == totalKinds) {
                found = true;
                while (start <= end) {
                    String startGem = gems[start];
                    if (gemCount.get(startGem) > 1) {
                        gemCount.put(startGem, gemCount.get(startGem) - 1);
                        start++;
                    } else {
                        break;
                    }
                }
                int curLength = end - start + 1;
                if (curLength < minLength) {
                    minLength = curLength;
                    resultStart = start;
                }
            }
            end++;
        }
        if (!found) {
            return new int[]{1, 1};
        }

        return new int[]{resultStart + 1, resultStart + minLength};
    }
}