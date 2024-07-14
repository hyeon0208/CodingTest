import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            int[] stocks = new int[N];
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                stocks[j] = Integer.parseInt(st.nextToken());
            }

            long profit = 0;
            long maxPrice = 0;
            for (int j = N - 1; j >= 0; j--) {
                if (stocks[j] > maxPrice) {
                    maxPrice = stocks[j];
                } else {
                    profit += maxPrice - stocks[j];
                }
            }

            sb.append(profit).append("\n");
        }

        System.out.println(sb);
    }
}
