import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int ans = 0;
        if (N == 1) {
            ans = 1;
        }
        if (N == 2) {
            ans = Math.min(4, (M + 1) / 2);
        }
        if (N >= 3) {
            if (M >= 7) {
                ans = M - 2;
            } else {
                ans = Math.min(4, M);
            }
        }
        System.out.println(ans);
    }
}


