import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int S = Integer.parseInt(br.readLine());

        String[] words = new String[S];
        for (int i = 0; i < S; i++) {
            words[i] = br.readLine();
        }

        int maxPrefix = -1;
        int result1 = 0;
        int result2 = 0;

        for (int i = 0; i < S - 1; i++) {
            for (int j = i + 1; j < S; j++) {
                if (words[i].equals(words[j])) {
                    continue;
                }

                int prefixLength = getCommonPrefixLength(words[i], words[j]);

                if (prefixLength > maxPrefix) {
                    maxPrefix = prefixLength;
                    result1 = i;
                    result2 = j;
                } else if (prefixLength == maxPrefix && prefixLength > 0) {
                    if (i < result1) {
                        result1 = i;
                        result2 = j;
                    } else if (i == result1 && j < result2) {
                        result2 = j;
                    }
                }
            }
        }

        System.out.println(words[result1]);
        System.out.println(words[result2]);
    }

    static int getCommonPrefixLength(String s1, String s2) {
        int length = 0;
        int minLength = Math.min(s1.length(), s2.length());

        for (int i = 0; i < minLength; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                length++;
            } else {
                break;
            }
        }
        return length;
    }
}
