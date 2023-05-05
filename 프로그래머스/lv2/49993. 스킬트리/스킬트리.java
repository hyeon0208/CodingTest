class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        
        for (int i = 0; i < skill_trees.length; i++) {
            String order = skill_trees[i].replaceAll("[^" + skill + "]", "");
            if (skill.startsWith(order)) {
                answer ++;
            }
        } 
        return answer;
    }
}