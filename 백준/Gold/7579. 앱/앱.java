import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/7579

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 확보해야할 메모리

        int[] memory = new int[N];
        int[] cost = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            memory[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int totalCost = 0;
        for (int i = 0; i < N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }

        // dp[i] = 비용 i로 얻을 수 있는 최대 메모리
        int[] dp = new int[totalCost + 1];

        for (int i = 0; i < N; i++) {
            // 역순으로 진행하여 중복 사용 방지
            for (int j = totalCost; j >= cost[i]; j--) {
                // 같은 비용으로 더 많은 메모리를 얻을 수 있다면 갱신
                dp[j] = Math.max(dp[j], dp[j - cost[i]] + memory[i]); // i 번째 앱을 비활성화하지 않는 경우 , 비활성화하는 경우
                // j - cost[i]: i번째 앱 비용을 제외한 나머지 비용
                // + memory[i]: i번째 앱을 비활성화해서 얻는 메모리
            }
        }

        // M 이상의 메모리를 확보할 수 있는 최소 비용 찾기
        for (int i = 0; i <= totalCost; i++) {
            if (dp[i] >= M) {
                System.out.println(i);
                return;
            }
        }
    }
}
