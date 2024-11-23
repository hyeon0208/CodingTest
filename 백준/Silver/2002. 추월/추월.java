import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Map<String, Integer> entryOrder = new HashMap<>();
        String[] entry = new String[N];
        for (int i = 0; i < N; i++) {
            String carNumber = br.readLine();
            entryOrder.put(carNumber, i);
            entry[i] = carNumber;
        }

        boolean[] checked = new boolean[N];
        int overtakeCount = 0;
        for(int i = 0; i < N; i++) {
            String exitCar = br.readLine();
            int exitOrder = entryOrder.get(exitCar);
            for(int j = 0; j < exitOrder; j++) {
                if(!checked[j]) {
                    overtakeCount++;
                    break;
                }
            }
            checked[exitOrder] = true;
        }

        System.out.println(overtakeCount);
    }
}
