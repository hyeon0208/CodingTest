import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        double ans = Double.MAX_VALUE;

        for (int i = K; i <= N; i++) {
            for (int j = 0; j <= N - i; j++) {
                double m = calculateMean(arr, j, i);
                double stdDev = calculateStdDev(arr, m, j, i);
                ans = Math.min(ans, stdDev);
            }
        }

        System.out.println(String.format("%.11f", ans));
    }

    // 평균 계산
    public static double calculateMean(int[] arr, int start, int length) {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            sum += arr[start + i];
        }
        return sum / length;
    }

    // 표준편차 계산
    public static double calculateStdDev(int[] arr, double mean, int start, int length) {
        double sum = 0;
        for (int i = 0; i < length; i++) {
            double diff = arr[start + i] - mean;
            sum += diff * diff;
        }
        return Math.sqrt(sum / length);
    }
}
