import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final int MOD = 1000000;
    private static final int PISANO_PERIOD = 1500000; // 피사노 주기 = 15 * 10^(k - 1)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());

        int realIndex = (int)(n % PISANO_PERIOD);

        int[] fib = new int[realIndex + 1];
        fib[0] = 0;
        fib[1] = 1;

        for (int i = 2; i <= realIndex; i++) {
            fib[i] = (fib[i-1] + fib[i-2]) % MOD;
        }

        System.out.println(fib[realIndex]);
    }
}
