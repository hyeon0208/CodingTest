import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
		String[] words = s.toLowerCase().split("");
		
		boolean flag = true;
		
		for (String word : words) {
			answer += flag ? word.toUpperCase() : word;
			flag = word.equals(" ") ? true : false;
		}

		return answer;
    }
}