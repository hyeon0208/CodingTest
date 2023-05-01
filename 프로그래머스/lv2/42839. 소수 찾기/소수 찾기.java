import java.util.HashSet;
import java.util.Set;

class Solution {
    static Set<Integer> set = new HashSet<>();
    static boolean[] visited = new boolean[10];
    
    public int solution(String numbers) {
        int answer = 0;
        
        permutation("", numbers);

        for (Integer num : set) {
            if (isPrime(num)) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public void permutation(String str, String numbers) {
        int n = numbers.length();
        if(!str.equals("")) {
            set.add(Integer.valueOf(str));
        }
        for (int i = 0; i < n; i++) {
            permutation(str + numbers.charAt(i), numbers.substring(0, i) + numbers.substring(i + 1));
        }
    }

    public static boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }

        for (int i = 2; i <= (int) Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }
}