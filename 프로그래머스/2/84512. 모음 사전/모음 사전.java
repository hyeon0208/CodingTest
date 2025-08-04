import java.util.*;

class Solution {
    private List<String> words;
    private static String[] alpabets = {"A", "E", "I", "O", "U"};
    
    public int solution(String word) {
        words = new ArrayList<>();
        dfs(word, "", 0);
        Collections.sort(words);
        return words.indexOf(word) + 1;
    }
    
    private void dfs(String word, String c, int depth) {
        if (words.contains(word)) {
            return;
        }
        if (depth == 5) {
            return;
        }
        for (int i = 0; i < alpabets.length; i++) {
            String newAlpabet = c + alpabets[i];
            words.add(newAlpabet);
            dfs(word, newAlpabet, depth + 1);
        }
    }
}