import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int N;
    static char[] current, target;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        current = br.readLine().toCharArray();
        target = br.readLine().toCharArray();

        // 첫 번째 스위치를 누르지 않는 경우
        char[] temp1 = current.clone();
        int count1 = solve(temp1, 0);

        // 첫 번째 스위치를 누르는 경우
        char[] temp2 = current.clone();
        flip(temp2, 0);
        int count2 = solve(temp2, 1);

        if (count1 == Integer.MAX_VALUE && count2 == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(Math.min(count1, count2));
        }
    }

    static int solve(char[] arr, int count) {
        for (int i = 1; i < N; i++) {
            if (arr[i - 1] != target[i - 1]) {
                flip(arr, i);
                count++;
            }
        }
        if (Arrays.equals(arr, target)) {
            return count;
        }
        return Integer.MAX_VALUE;
    }

    static void flip(char[] arr, int index) {
        for (int i = index - 1; i <= index + 1; i++) {
            if (i >= 0 && i < N) {
                arr[i] = arr[i] == '0' ? '1' : '0';
            }
        }
    }
}
