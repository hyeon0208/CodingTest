import java.util.*;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];

        Stack<String> stack = new Stack<>();
        for (int i = 0; i < words.length; i += n) {
            answer[1]++;
            for (int j = i; j < i + n; j++) {
                if (stack.contains(words[j])) {
                    answer[0] = (j % n) + 1;
                    return answer;
                }

                if (!stack.isEmpty()) {
                    String peek = stack.peek();
                    if (peek.charAt(peek.length() - 1) != words[j].charAt(0)) {
                        answer[0] = (j % n) + 1;
                        return answer;
                    }
                }

                stack.add(words[j]);
            }
        }

        answer[0] = 0;
        answer[1] = 0;
        return answer;
    }
}