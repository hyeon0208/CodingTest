import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static Set<Character> usedShortcuts = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String option = br.readLine();
            String result = processOption(option);
            System.out.println(result);
        }
    }

    static String processOption(String option) {
        String[] words = option.split(" ");

        // 1단계: 각 단어의 첫 글자를 단축키로 시도
        String result = tryFirstLetterShortcut(words);
        if (result != null) {
            return result;
        }

        // 2단계: 왼쪽에서 오른쪽으로 모든 글자를 단축키로 시도
        result = tryAllLettersShortcut(words);
        if (result != null) {
            return result;
        }

        // 단축키를 찾지 못한 경우 원본 반환
        return option;
    }

    static String tryFirstLetterShortcut(String[] words) {
        for (int i = 0; i < words.length; i++) {
            char firstChar = Character.toUpperCase(words[i].charAt(0));
            if (Character.isLetter(firstChar) && !usedShortcuts.contains(firstChar)) {
                usedShortcuts.add(firstChar);
                return buildResultString(words, i, 0);
            }
        }
        return null;
    }

    static String tryAllLettersShortcut(String[] words) {
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char ch = Character.toUpperCase(word.charAt(j));
                if (Character.isLetter(ch) && !usedShortcuts.contains(ch)) {
                    usedShortcuts.add(ch);
                    return buildResultString(words, i, j);
                }
            }
        }
        return null;
    }

    static String buildResultString(String[] words, int wordIndex, int charIndex) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                result.append(" ");
            }

            if (i == wordIndex) {
                String word = words[i];
                result.append(word.substring(0, charIndex))
                        .append("[")
                        .append(word.charAt(charIndex))
                        .append("]")
                        .append(word.substring(charIndex + 1));
            } else {
                result.append(words[i]);
            }
        }

        return result.toString();
    }
}
