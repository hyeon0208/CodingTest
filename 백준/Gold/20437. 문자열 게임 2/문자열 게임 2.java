import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < T; i++) {
            String word = br.readLine();
            int K = Integer.parseInt(br.readLine());

            if (K == 1) {
                sb.append("1 1").append("\n");
                continue;
            }

            int[] alpha = new int[26];

            for (int a = 0; a < word.length(); a++) {
                alpha[word.charAt(a) - 'a']++;
            }

            int min = Integer.MAX_VALUE;
            int max = -1;

            for (int j = 0; j < word.length(); j++) {
                if (alpha[word.charAt(j) - 'a'] < K) {
                    continue;
                }

                int equalCnt = 1;

                for (int k = j + 1; k < word.length(); k++) {
                    if (word.charAt(j) == word.charAt(k)) {
                        equalCnt++;
                    }

                    if (equalCnt == K) {
                        min = Math.min(min, k - j + 1);
                        max = Math.max(max, k - j + 1);
                        break;
                    }
                }
            }

            if (min == Integer.MAX_VALUE || max == -1) {
                sb.append("-1").append("\n");
                continue;
            }
            sb.append(min).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }
}
