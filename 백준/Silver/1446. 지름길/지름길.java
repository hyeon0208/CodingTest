import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int D;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        List<List<FastRoad>> graph = new ArrayList<>(10001);
        int[] distance = new int[10001];

        for (int i = 0; i < 10001; i++) {
            graph.add(new ArrayList<>(N));
            distance[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());

            if (start > D || end > D) {
                continue;
            }
            graph.get(start).add(new FastRoad(end, length));

        }

        dijkstra(graph, distance, 0);
        System.out.println(distance[D]);
    }


    private static void dijkstra(List<List<FastRoad>> graph, int[] distance, int start) {
        if (start > D) {
            return;
        }

        if (distance[start + 1] > distance[start] + 1) {
            distance[start + 1] = distance[start] + 1;
        }

        for (int i = 0; i < graph.get(start).size(); i++) {
            if (distance[start] + graph.get(start).get(i).roadLength < distance[graph.get(start).get(i).endPosition]) {
                distance[graph.get(start).get(i).endPosition] = distance[start] + graph.get(start).get(i).roadLength;
            }
        }

        dijkstra(graph, distance, start + 1);
    }

    private static class FastRoad {
        int endPosition;
        int roadLength;

        public FastRoad(int endPosition, int roadLength) {
            this.endPosition = endPosition;
            this.roadLength = roadLength;
        }

        @Override
        public String toString() {
            return "FastRoad{" +
                    "endPosition=" + endPosition +
                    ", roadLength=" + roadLength +
                    '}';
        }
    }
}
