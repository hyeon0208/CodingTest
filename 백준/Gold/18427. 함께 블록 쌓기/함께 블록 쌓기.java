import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int H;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 학생 수
        M = Integer.parseInt(st.nextToken()); // 학생마다 가질 수 있는 최대 블록 개수
        H = Integer.parseInt(st.nextToken()); // 쌓고자 하는 블록 높이

        List<Integer>[] blocks = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            blocks[i] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                blocks[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // dp[i][h] = i번째 학생까지 고려했을 때 높이 h를 만드는 경우의 수
        int[][] dp = new int[N + 1][H + 1];
        dp[0][0] = 1; // 아무 블록도 안 쌓았을 때 높이 0을 만드는 경우의 수는 1

        for (int i = 0; i < N; i++) {
            for (int h = 0; h <= H; h++) {
                if (dp[i][h] == 0) {
                    continue;
                }

                // 다음 학생이 이전 학생의 높이를 그대로 가져가는 경우
                dp[i + 1][h] = (dp[i + 1][h] + dp[i][h]) % 10007;

                // 현재 학생의 각 블록을 쌓아보는 경우
                for (int block : blocks[i]) {
                    if (h + block <= H) {
                        dp[i + 1][h + block] = (dp[i + 1][h + block] + dp[i][h]) % 10007;
                    }
                }
            }
        }

        System.out.println(dp[N][H]);
    }
}
