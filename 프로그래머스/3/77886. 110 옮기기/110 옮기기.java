import java.util.Arrays;

class Solution {
    
    public static String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            StringBuilder result = new StringBuilder();
            int xCount = 0;

            for (char c : s[i].toCharArray()) {
                result.append(c);
                int length = result.length();

                if (length >= 3 &&
                        result.charAt(length-3) == '1' &&
                        result.charAt(length-2) == '1' &&
                        result.charAt(length-1) == '0') {
                    result.setLength(length - 3);
                    xCount++;
                }
            }

            int lastZeroIndex = -1;
            if (hasZero(result)) {
                lastZeroIndex = findLastZeroIndex(result);
            }

            if (lastZeroIndex != -1) {
                StringBuilder temp = new StringBuilder();
                temp.append(result.substring(0, lastZeroIndex + 1));
                for (int j = 0; j < xCount; j++) {
                    temp.append("110");
                }
                temp.append(result.substring(lastZeroIndex + 1));
                answer[i] = temp.toString();
            } else {
                StringBuilder temp = new StringBuilder();
                for (int j = 0; j < xCount; j++) {
                    temp.append("110");
                }
                temp.append(result);
                answer[i] = temp.toString();
            }
        }

        return answer;
    }

    private static boolean hasZero(StringBuilder result) {
        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '0') {
                return true;
            }
        }
        return false;
    }

    private static int findLastZeroIndex(StringBuilder result) {
        int index = 0;
        for (int i = result.length() - 1; i >= 0; i--) {
            if (result.charAt(i) == '0') {
                index = i;
                break;
            }
        }
        return index;
    }

    private static void addBack(StringBuilder result, int lastZeroIndex) {
        result.insert(lastZeroIndex + 1, "110");
    }

    private static void addFront(StringBuilder result) {
        result.insert(0, "110");
    }
}