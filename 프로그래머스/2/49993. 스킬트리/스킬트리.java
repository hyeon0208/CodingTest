import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int result = 0;
        
        for (String str : skill_trees) {
            String temp = "";
            for (int i = 0; i < str.length(); i++) {
                String c = String.valueOf(str.charAt(i));
                if (skill.contains(c)) {
                    temp += c;
                }
            }   
            if (skill.startsWith(temp)) {
                result++;
            }
        }
        
        return result;
    }
}