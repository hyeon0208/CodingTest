import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        List<Integer> ranks = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            ranks.add(Integer.parseInt(br.readLine()));
        }
        ranks.sort(Comparator.comparingInt(value -> value));

        int rank = 1;
        long result = 0;
        for (int i = 0; i < N; i++) {
            result += Math.abs(ranks.get(i) - rank);
            rank++;
        }

        System.out.println(result);
    }
}
