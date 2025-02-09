import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] children = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        // dp[i]는 i번째 원소를 마지막으로 하는 최장 증가 수열의 길이
        int[] dp = new int[N];
        int maxLength = 1; // 초기 최대 LIS 길이

        for (int i = 0; i < N; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (children[i] > children[j]) { // 현재 값보다 작은 값이 있다면 부분 수열 길이 갱신
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }

        // LIS에 속한 아이들은 이미 올바른 순서로 서있기 때문에 이보다 이미 정렬된 수열은 없음
        // 따라서 LIS에 속하지 않은 아이들이 이동해야 옮겨지는 아이의 최소 수를 구할 수 있음.
        System.out.println(N - maxLength);
    }
}
