import java.io.*;

public class Main {
    private static int[] levels;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        levels = new int[N];
        for (int i = 0; i < N; i++) {
            levels[i] = Integer.parseInt(br.readLine());
        }
        int count = 0;
        while (!isAscending()) {
            for (int i = 0; i < N - 1; i++) {
                if (isGreaterAndEqualsThanFront(i + 1)) {
                    continue;
                }
                count++;
                levels[i]--;
            }
        }
        System.out.println(count);
    }

    private static boolean isGreaterAndEqualsThanFront(int nextIndex) {
        if (levels[nextIndex] > levels[nextIndex - 1]) {
            return true;
        }
        return false;
    }

    private static boolean isAscending() {
        for (int i = 1; i < levels.length; i++) {
            if (levels[i] <= levels[i - 1]) {
                return false;
            }
        }
        return true;
    }
}