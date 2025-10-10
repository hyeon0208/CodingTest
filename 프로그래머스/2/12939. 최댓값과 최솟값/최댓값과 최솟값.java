import java.util.*;
 
class Solution {
  public String solution(String s) {
    String answer = "";
    List<Integer> results = new ArrayList<>();
    
    String[] arr = s.split(" ");
    
    for (String a : arr) {
        results.add(Integer.parseInt(a));
    }
     
    answer += Collections.min(results);
    answer += " " + Collections.max(results);
    
    return answer;
  }
}
