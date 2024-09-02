import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<Integer> arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(arr);
        int left = 0;
        int right = N - 1;
        int mid = Integer.MAX_VALUE;
        int[] results = new int[2];
        while (left < right) {
            int sum = arr.get(left) + arr.get(right);

            if (Math.abs(sum) < mid) {
                mid = Math.abs(sum);
                results[0] = arr.get(left);
                results[1] = arr.get(right);
            }

            if (sum > 0) {
                right--;
            } else if (sum < 0) {
                left++;
            } else {
                break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int result : results) {
            sb.append(result).append(" ");
        }
        System.out.println(sb);
    }
}
