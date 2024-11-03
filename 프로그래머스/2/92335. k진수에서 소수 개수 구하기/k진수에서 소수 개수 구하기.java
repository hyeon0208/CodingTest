import java.util.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String binary = Integer.toString(n, k);
        List<String> nums = new ArrayList<>(List.of(binary.split("0")));

        for (String str : nums) {
            if (str.equals("")) {
                continue;
            }
            long checkNum = Long.parseLong(str);
            if (isPrime(checkNum)) {
                answer++;
            }
        }

        return answer;
    }

    public static boolean isPrime(long checkNum) {
        if (checkNum == 1) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(checkNum); i++) {
            if (checkNum % i == 0) {
                return false;
            }
        }
        return true;
    }
}