import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int result = 0;

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] list = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        List<Integer> friends = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            friends.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            if (friends.contains(list[i])) {
                result -= 1;
            }
        }

        System.out.println(result + M);
    }
}
