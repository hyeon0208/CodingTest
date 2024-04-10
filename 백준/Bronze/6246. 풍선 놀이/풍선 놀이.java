import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int step;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        boolean[] balloon = new boolean[N];

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken()) - 1;
            int I = Integer.parseInt(st.nextToken());

            balloon[L] = true;

            step = L;
            while (step <= N - 1) {
                balloon[step] = true;
                step += I;
            }

        }

        int result = 0;
        for (boolean b : balloon) {
            if (!b) {
                result++;
            }
        }
        
        System.out.println(result);
    }
}