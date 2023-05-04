import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
 
class Solution {
  public String solution(String s) {
    String answer = "";
    List<Integer> results = new ArrayList<>();
    String[] sArr = s.split(" ");

    for (String num : sArr) {
        results.add(Integer.parseInt(num));
    }

    answer += Collections.min(results) + " " + Collections.max(results);

    return answer;
  }
}
