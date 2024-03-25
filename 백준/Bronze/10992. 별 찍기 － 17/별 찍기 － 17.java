import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            sb.append(" ".repeat( N - i));

            if (i == N) {
                sb.append("*".repeat(2 * i - 1));
            } else {
                for(int j = 0; j < 2 * i - 1; j++) {
                    if(j == 0 || j == 2 * i - 1 - 1) {
                        sb.append("*");
                    } else {
                        sb.append(" ");
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}