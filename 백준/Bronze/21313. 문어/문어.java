import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean odd = false;

        if (N % 2 != 0) {
            odd = true;
            N--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (i % 2 != 0) {
                sb.append("1 ");
            }
            if (i % 2 == 0) {
                sb.append("2 ");
            }
        }
        if (odd) {
            sb.append("3");
        }

        System.out.println(sb);
    }
}
