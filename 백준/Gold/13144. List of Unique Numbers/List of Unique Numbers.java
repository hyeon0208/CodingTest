import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] numbers;
    private static boolean[] used;
    private static long result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        used = new boolean[100001];
        int start = 0;
        int end = 0;
        while (start < N) {
            while (end < N && !used[numbers[end]]) {
                used[numbers[end]] = true;
                end++;
            }
            result += end - start;

            used[numbers[start]] = false;
            start++;
        }

        System.out.println(result);
    }
}
