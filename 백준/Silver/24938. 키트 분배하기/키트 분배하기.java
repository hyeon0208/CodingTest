import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] rooms = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            rooms[i] = Integer.parseInt(st.nextToken());
        }

        int average = (int) Arrays.stream(rooms).average().getAsDouble();

        int result = 0;
        for (int i = 0; i < N - 1; i++) {
            if (rooms[i] < average) {
                int temp = average - rooms[i];
                rooms[i + 1] -= temp;
                result += Math.abs(temp);
            }
        }

        System.out.println(result);
    }
}
