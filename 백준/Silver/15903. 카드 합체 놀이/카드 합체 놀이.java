import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Long.valueOf(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            long xNumber = pq.poll();
            long yNumber = pq.poll();
            long sumNumber = xNumber + yNumber;
            xNumber = sumNumber;
            yNumber = sumNumber;
            pq.add(xNumber);
            pq.add(yNumber);
        }

        long sum = pq.stream()
                .mapToLong(Long::longValue)
                .sum();

        System.out.println(sum);
    }
}


