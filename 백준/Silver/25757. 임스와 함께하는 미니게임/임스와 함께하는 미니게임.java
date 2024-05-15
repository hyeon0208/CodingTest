import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    private static final Map<String, Integer> PARTICIPANTS_COUNT = Map.of("Y", 2, "F", 3, "O", 4);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int playerCount = Integer.parseInt(st.nextToken());
        int limit = PARTICIPANTS_COUNT.get(st.nextToken());

        Set<String> player = new HashSet<>();
        for (int i = 0; i < playerCount; i++) {
            player.add(br.readLine());
        }

        System.out.println(player.size() / (limit - 1));
    }
}