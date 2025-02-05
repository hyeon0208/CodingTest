import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static List<Computer>[] computers;
    private static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 컴퓨터 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터의 번호

            distance = new int[n + 1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            computers = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                computers[i] = new ArrayList<>();
            }
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken()); // b가 감염되면 s초 후 a 컴퓨터가 감염
                computers[b].add(new Computer(a, s));
            }
            dijkstra(c);

            int infectedCount = 0;
            int total = 0;
            for (int i = 1; i <= n; i++) {
                if (distance[i] != Integer.MAX_VALUE) {
                    infectedCount++;
                    total = Math.max(total, distance[i]);
                }
            }
            System.out.println(infectedCount + " " + total);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Computer> pq = new PriorityQueue<>(Comparator.comparingInt(value -> value.hackingTime));
        distance[start] = 0;
        pq.offer(new Computer(start, 0));

        while (!pq.isEmpty()) {
            Computer cur = pq.poll();
            for (Computer next : computers[cur.num]) {
                int nextTime = next.hackingTime + cur.hackingTime;
                if (distance[next.num] > nextTime) {
                    distance[next.num] = nextTime;
                    pq.offer(new Computer(next.num, distance[next.num]));
                }
            }
        }
    }

    private static class Computer {
        int num;
        int hackingTime;

        public Computer(int num, int hackingTime) {
            this.num = num;
            this.hackingTime = hackingTime;
        }
    }
}
