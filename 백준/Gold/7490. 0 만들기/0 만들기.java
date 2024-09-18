import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static StringBuilder sb = new StringBuilder();
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            dfs(1, 1, "1");
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int num, int depth, String str) {
        if (depth == N) {
            if (calculate(str) == 0) {
                sb.append(str + "\n");
            }
            return;
        }

        dfs(num + 1, depth + 1, str + ' ' + (num + 1));
        dfs(num + 1, depth + 1, str + '+' + (num + 1));
        dfs(num + 1, depth + 1, str + '-' + (num + 1));

    }

    private static int calculate(String str) {
        str = str.replaceAll(" ", "");
        StringTokenizer st = new StringTokenizer(str, "[+,-]", true);
        int result = Integer.parseInt(st.nextToken());

        while (st.hasMoreTokens()) {
            String next = st.nextToken();

            if (next.equals("+")) {
                result += Integer.parseInt(st.nextToken());
            }
            if (next.equals("-")) {
                result -= Integer.parseInt(st.nextToken());
            }
        }
        return result;
    }
}
