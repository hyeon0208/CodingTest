import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String line = br.readLine();
            int depth = 0;
            int maxDepth = 0;

            for (char c : line.toCharArray()) {
                if (c == '[') {
                    depth++;
                }
                if (c == ']') {
                    depth--;
                }
                maxDepth = Math.max(maxDepth, depth);
            }

            System.out.println((int) Math.pow(2, maxDepth));
        }
    }
}
