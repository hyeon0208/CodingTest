import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int C;
    private static int[] locations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        locations = new int[N];
        for (int i = 0; i < N; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(locations);

        int left = 1;  // 최소 거리
        int right = locations[N - 1] - locations[0];  // 최대 거리
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (getInstallCount(mid) >= C) {
                result = mid;  // 가능한 경우 결과 저장
                left = mid + 1;  // 더 큰 거리 탐색
            } else {
                right = mid - 1;  // 더 작은 거리 탐색
            }
        }
        System.out.println(result);
    }

    private static int getInstallCount(int distance) {
        int count = 1;
        int lastLocate = locations[0];

        for (int i = 1; i < locations.length; i++) {
            int locate = locations[i];
            if (locate - lastLocate >= distance) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}
