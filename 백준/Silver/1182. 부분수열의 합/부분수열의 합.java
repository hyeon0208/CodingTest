import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    private static int N; // 정수의 개수
    private static int S; // 정수의 합
    static int result;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        nums = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        if (S == 0) {
            System.out.println(result - 1);
        }
        if (S != 0) {
            System.out.println(result);
        }
    }

    public static void dfs(int depth, int sum) {
        if (depth == N) {
            if (sum == S) {
                result++;
            }
            return;
        }
        dfs(depth + 1, sum + nums[depth]);
        dfs(depth + 1, sum);
    }
}

