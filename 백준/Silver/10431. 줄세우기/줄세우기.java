import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i < T + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int testNumber = Integer.parseInt(st.nextToken());
            int result = 0;
            int[] students = new int[20];
            for (int j = 0; j < 20; j++) {
                students[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < j; k++) {
                    if (students[k] > students[j]) {
                        result++;
                    }
                }
            }
            System.out.println(testNumber + " " + result);
        }
    }
}
