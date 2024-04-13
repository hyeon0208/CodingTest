import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        long[] cross = new long[N];
        long[] left = new long[N];
        long[] right = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cross[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            left[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N - 1; i++) {
            right[i] = Integer.parseInt(st.nextToken());
        }


        long[] leftSum = new long[N];
        long sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += left[i];
            leftSum[i + 1] = sum;
        }

        long[] rightSum = new long[N];
        sum = 0;
        for (int i = N - 2; i >= 0; i--) {
            sum += right[i];
            rightSum[i] = sum;
        }

        long minCost = Long.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < N; i++) {
            long totalCost = cross[i] + leftSum[i] + rightSum[i];
            if (totalCost < minCost) {
                minCost = totalCost;
                index = i + 1;
            }
        }

        System.out.println(index + " " + minCost);
    }
}
