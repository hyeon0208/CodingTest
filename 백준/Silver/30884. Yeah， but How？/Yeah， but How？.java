import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < line.length() - 1; i++) {
            sb.append(line.charAt(i));

            if (line.charAt(i) == '(' && line.charAt(i + 1) == ')') {
                sb.append('1');
            }
            if (line.charAt(i) == ')' && line.charAt(i + 1) == '(') {
                sb.append('+');
            }
        }
        sb.append(line.charAt(line.length() - 1));

        System.out.println(sb);
    }
}
