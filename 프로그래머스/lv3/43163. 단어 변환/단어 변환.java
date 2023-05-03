import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean[] visited = new boolean[words.length];
        
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(begin, 0));
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.word.equals(target)) {
                answer = cur.count;
                break;
            }
            
            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && isNext(cur.word, words[i])) {
                    visited[i] = true;
                    queue.add(new Node(words[i], cur.count + 1));
                }
            }
        }        
        
        return answer;
    }
    
    public boolean isNext(String curWord, String nextWord) {
        int diff = 0;
        for (int i = 0; i < nextWord.length(); i++) {
            if (curWord.charAt(i) != nextWord.charAt(i)) {
                diff++;
            }
        }
        return diff == 1 ? true : false;
    }
    
    private static class Node {
        String word;
        int count;
    
        public Node(String word, int count) {
            this.word = word;
            this.count = count;
        }
    }
}

