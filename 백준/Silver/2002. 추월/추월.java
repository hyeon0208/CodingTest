import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> entryOrder = new HashMap<>();
        String[] entry = new String[n];

        for (int i = 0; i < n; i++) {
            String carNumber = br.readLine();
            entryOrder.put(carNumber, i);
            entry[i] = carNumber;
        }

        String[] exit = new String[n];
        for (int i = 0; i < n; i++) {
            exit[i] = br.readLine();
        }

        int overtakeCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 현재 차량의 입구 순서
                int curr = entryOrder.get(exit[i]);
                // 비교할 차량의 입구 순서
                int next = entryOrder.get(exit[j]);

                // 더 늦게 들어온 차량이 먼저 나왔다면 추월한 것
                if (curr > next) {
                    overtakeCount++;
                    break;
                }
            }
        }
        System.out.println(overtakeCount);
    }
}
