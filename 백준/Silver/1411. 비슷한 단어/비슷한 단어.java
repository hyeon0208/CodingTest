import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        String[] words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        int count = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (isSimilar(words[i], words[j])) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    private static boolean isSimilar(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }

        HashMap<Character, Character> mapping = new HashMap<>();
        HashSet<Character> used = new HashSet<>();

        for (int i = 0; i < word1.length(); i++) {
            char c1 = word1.charAt(i);
            char c2 = word2.charAt(i);

            // c1이 이미 매핑되어 있는 경우
            if (mapping.containsKey(c1)) {
                if (mapping.get(c1) != c2) {
                    return false;
                }
            } else {
                // c1이 아직 매핑되지 않았지만, c2가 이미 다른 문자에 매핑되어 있는 경우
                if (used.contains(c2)) {
                    return false;
                }

                mapping.put(c1, c2);
                used.add(c2);
            }
        }

        return true;
    }
}
