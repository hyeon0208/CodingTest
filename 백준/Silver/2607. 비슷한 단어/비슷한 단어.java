import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        String answerWord = br.readLine();
        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            String otherWord = br.readLine();
            int cnt = 0;
            int[] word = new int[26];
            for (int j = 0; j < answerWord.length(); j++) {
                word[answerWord.charAt(j) - 'A']++;
            }

            for (int j = 0; j < otherWord.length(); j++) {
                if (word[otherWord.charAt(j) - 'A'] > 0) {
                    cnt++;
                    word[otherWord.charAt(j) - 'A']--;
                }
            }

            if (answerWord.length() == otherWord.length() && (answerWord.length() == cnt
                                                              || answerWord.length() - 1 == cnt)) {
                result++;
            } else if (answerWord.length() == otherWord.length() - 1 && otherWord.length() - 1 == cnt) {
                result++;
            } else if (answerWord.length() == otherWord.length() + 1 && otherWord.length() == cnt) {
                result++;
            }

        }
        System.out.println(result);
    }
}
