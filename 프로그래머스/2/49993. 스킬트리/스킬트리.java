import java.util.*;

class Solution {
    public int solution(String skill, String[] skill_trees) {
        int result = 0;
        
        for (String tree : skill_trees) {
            if (find(skill, tree)) {
                result++;
            }
        }

        return result;
    }
    
    private boolean find(String skill, String tree) {
        int skillIndex = 0;
            
        for (char t : tree.toCharArray()) {
            int idx = skill.indexOf(t);

            if (idx == -1) {
                continue;
            }

            if (idx == skillIndex) {
                skillIndex++;
            } else {
                return false;
            }
        }
        return true;
    }
}