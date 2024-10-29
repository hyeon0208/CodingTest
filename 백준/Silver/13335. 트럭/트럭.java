import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int W;
    private static int L;
    private static int[] truckWeights;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        truckWeights = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            truckWeights[i] = Integer.parseInt(st.nextToken());
        }

        Bridge bridge = new Bridge(W, L);
        for (int i = 0; i < N; i++) {
            while (!bridge.canOn(truckWeights[i])) {
                bridge.move();
            }
            bridge.on(truckWeights[i]);
        }

        result += W;

        System.out.println(result);
    }

    private static class Bridge {
        int length;
        int maxWeight;
        Queue<Integer> trucks;

        public Bridge(int length, int maxWeight) {
            this.length = length;
            this.maxWeight = maxWeight;
            this.trucks = new LinkedList<>();
            for (int i = 0; i < length; i++) {
                trucks.add(0);
            }
        }

        public void on(int truckWeight) {
            out();
            trucks.add(truckWeight);
            result++;
        }

        public boolean canOn(int truckWeight) {
            int currentWeight = getWeightSum() - trucks.peek();
            return currentWeight + truckWeight <= maxWeight;
        }

        public void move() {
            out();
            trucks.add(0);
            result++;
        }

        public void out() {
            trucks.poll();
        }

        public int getWeightSum() {
            return trucks.stream().mapToInt(Integer::intValue).sum();
        }
    }
}
