import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] distances;
    private static int[] prices;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        distances = new int[N - 1];
        prices = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            distances[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            prices[i] = Integer.parseInt(st.nextToken());
        }

        long totalCost = 0;
        long minPrice = prices[0];

        for (int i = 0; i < N-1; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            totalCost += minPrice * distances[i];
        }

        System.out.println(totalCost);
    }
}
