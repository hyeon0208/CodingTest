import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[N];

        char[] inputs = br.readLine().toCharArray();

        int result = 0;
        for (int i = 0; i < N; i++) {
            if (inputs[i] == 'P') {
                for (int j = -K; j <= K; j++) {
                    if (i + j >= N) {
                        break;
                    }
                    if (i + j < 0) {
                        continue;
                    }

                    if(inputs[i + j] == 'H' && !visited[i + j]) {
                        visited[i+j] = true;
                        result++;
                        break;
                    }
                }
            }
        }

        System.out.println(result);
    }
}
