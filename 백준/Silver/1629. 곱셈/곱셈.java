import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        long C = Long.parseLong(st.nextToken());

        System.out.println(power(A, B, C));
    }

    // 분할 정복을 이용한 거듭제곱 계산
    private static long power(long A, long B, long C) {
        if (B == 1) {
            return A % C;
        }

        // 지수를 절반으로 나누어 계산
        long temp = power(A, B/2, C);

        // 지수가 짝수인 경우
        if (B % 2 == 0) {
            return ((temp % C) * (temp % C)) % C;
        } else {
            return ((((temp % C) * (temp % C)) % C) * (A % C)) % C;
        }
    }
}
