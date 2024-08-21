import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static String S;
    private static String T;
    private static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();

        dfs(T);

        System.out.println(result);
    }

    private static void dfs(String newT) {
        if (S.length() == newT.length()) {
            if (S.equals(newT)) {
                result = 1;
            }
            return;
        }

        // 마지막이 A일 경우 끝의 A제거
        if (newT.endsWith("A")) {
            dfs(newT.substring(0, newT.length() - 1));
        }

        // B로 시작할 경우 첫 B를 제거 후 reverse
        if (newT.startsWith("B")) {
            dfs(new StringBuilder(newT.substring(1)).reverse().toString());
        }
    }
}
