import java.util.*;

public class Solution {
    public int solution(int n) {
        return jump(n);
    }
    
    private int jump(int n) {
        if (n == 1) {
            return 1;
        }
        
        if (n % 2 == 0) {
            return jump(n / 2);
        } else {
            return 1 + jump((n - 1) / 2);
        }
    }
}