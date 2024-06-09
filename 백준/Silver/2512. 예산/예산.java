import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] requestMoney = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requestMoney[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());

        Arrays.sort(requestMoney);
        int sum = Arrays.stream(requestMoney).sum();
        int maxAmount = requestMoney[N - 1];

        if (sum <= M) {
            System.out.println(maxAmount);
            return;
        }

        int start = 0;
        int end = maxAmount;
        int result = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            int total = 0;
            for (int money : requestMoney) {
                if (money >= mid) {
                    total += mid;
                } else {
                    total += money;
                }
            }
            if (total > M) {
                end = mid;
            } else {
                start = mid + 1;
                result = Math.max(result, mid);
            }
        }

        System.out.println(result);
    }
}
