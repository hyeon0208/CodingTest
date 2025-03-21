class Solution {
    
    private static String[] alpha;
    private static int answer;
    
    public static int solution(int n, String[] data) {
        alpha = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
        answer = 0;
        dfs(0, new StringBuilder(), data, new boolean[8]);

        return answer;
    }

    private static void dfs(int depth, StringBuilder str, String[] data, boolean[] used) {
        if (depth == alpha.length) {
            if (allCheck(str.toString(), data)) {
                answer++;
            }
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (!used[i]) {
                used[i] = true;
                str.append(alpha[i]);
                dfs(depth + 1, str, data, used);
                str.deleteCharAt(str.length() - 1);
                used[i] = false;
            }
        }
    }

    private static boolean allCheck(String str, String[] data) {
        for (String d : data) {
            char person1 = d.charAt(0);
            char person2 = d.charAt(2);
            char operator = d.charAt(3);
            int value = Character.getNumericValue(d.charAt(4));

            int pos1 = str.indexOf(person1);
            int pos2 = str.indexOf(person2);

            int distance = Math.abs(pos1 - pos2) - 1;

            if (operator == '=') {
                if (distance != value) {
                    return false;
                }
            } else if (operator == '<') {
                if (distance >= value) {
                    return false;
                }
            } else if (operator == '>') {
                if (distance <= value) {
                    return false;
                }
            }
        }
        return true;
    }
}