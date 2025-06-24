import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main { // https://www.acmicpc.net/problem/14888

    private static int max = Integer.MIN_VALUE;
    private static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] nums = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] operation = new int[4];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        backtracking(nums, operation, 1, nums[0]);

        System.out.println(max);
        System.out.println(min);
    }

    private static void backtracking(int[] nums, int[] operation, int depth, int sum) {
        if (depth == nums.length) {
            max = Math.max(max, sum);
            min = Math.min(min, sum);
            return;
        }

        for (int i = 0; i < operation.length; i++) {
            if (operation[i] != 0) {
                operation[i]--;
                int nextSum = 0;
                if (i == 0) { // 더하기
                    nextSum = sum + nums[depth];
                } else if (i == 1) { // 빼기
                    nextSum = sum - nums[depth];
                } else if (i == 2) { // 곱하기
                    nextSum = sum * nums[depth];
                } else { // 나누기
                    nextSum = sum / nums[depth];
                }
                backtracking(nums, operation, depth + 1, nextSum);
                operation[i]++;
            }
        }
    }
}
