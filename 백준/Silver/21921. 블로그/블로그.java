import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        int[] visiters = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visiters[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        for (int i = 0; i < X; i++) {
            sum += visiters[i];
        }

        int max = sum;
        int result = 1;
        for (int i = 0; i < N - X; i++) {
            sum += visiters[i + X];
            sum -= visiters[i];
            
            if (sum == max) {
                result++;
            }

            if (sum > max) {
                result = 1;
                max = sum;
            }
        }
        if (max == 0) {
            System.out.println("SAD");
        } else {
            System.out.println(max);
            System.out.println(result);
        }
    }
}
