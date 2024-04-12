import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(times);

        long left = 0;
        long right = (long) times[N - 1] * M; // 가장 느린 직원이 혼자서 모든 풍선을 만드는 최악의 시간
        while (left <= right) {
            long mid = (left + right) / 2; // 현재 주어진 시간
            long count = 0; // 주어진 시간 동안 만들 수 있는 풍선의 수

            // 현재 주어진 시간(mid) 동안 각 직원이 만들 수 있는 풍선의 수를 계산
            for (int i = 0; i < N; i++) {
                count += (mid / times[i]);
            }

            // 만들 수 있는 풍선의 수가 충분한 경우, 시간을 줄인다
            if (count >= M) {
                right = mid - 1;
            }
            // 만들 수 있는 풍선의 수가 부족한 경우, 시간을 늘린다
            if (count < M) {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }
}
