import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        boolean hasPrev = false;

        // 뒤에서부터 내림차순이 깨지는 위치 찾기
        int i = n - 1;
        while (i > 0 && arr[i - 1] <= arr[i]) {
            i--;
        }

        if (i > 0) {
            hasPrev = true;

            int j = n - 1;
            while (arr[j] >= arr[i - 1]) {
                j--;
            }

            int temp = arr[i - 1];
            arr[i - 1] = arr[j];
            arr[j] = temp;

            swap(arr, i, n - 1);
        }

        if (hasPrev) {
            StringBuilder sb = new StringBuilder();
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            System.out.println(sb.toString().trim());
        } else {
            System.out.println("-1");
        }
    }

    private static void swap(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
