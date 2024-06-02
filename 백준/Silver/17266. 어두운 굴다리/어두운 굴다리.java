import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int bridgeLength;
    static int[] lampPositions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        bridgeLength = Integer.parseInt(br.readLine());
        int lampCount = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        lampPositions = new int[lampCount];

        for (int i = 0; i < lampCount; i++) {
            lampPositions[i] = Integer.parseInt(st.nextToken());
        }

        int left = 1;
        int right = bridgeLength;
        int result = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (possible(mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean possible(int target) {
        int prev = 0;

        for (int i = 0; i < lampPositions.length; i++) {
            if (lampPositions[i] - target <= prev) {
                prev = lampPositions[i] + target;
            } else {
                return false;
            }
        }
        return bridgeLength - prev <= 0;
    }
}
