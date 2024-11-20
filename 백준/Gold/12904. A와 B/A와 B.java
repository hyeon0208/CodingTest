import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String S = br.readLine();
        String T = br.readLine();

        StringBuilder sb = new StringBuilder(T);

        System.out.println(canTransform(S, sb) ? 1 : 0);
    }

    private static boolean canTransform(String S, StringBuilder T) {
        while (T.length() > S.length()) {
            char lastChar = T.charAt(T.length() - 1);
            T.setLength(T.length() - 1);

            if (lastChar == 'B') {
                T.reverse();
            }
        }

        return T.toString().equals(S);
    }
}