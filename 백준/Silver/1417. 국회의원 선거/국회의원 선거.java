import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N <= 1) {
            System.out.println(0);
            return;
        }
        int dasom = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        while (N - 1 > 0) {
            pq.add(Integer.parseInt(br.readLine()));
            N--;
        }
        int result = 0;
        while (!pq.isEmpty() && pq.peek() >= dasom) {
            result++;
            dasom++;
            pq.add(pq.poll() - 1);
        }
        System.out.println(result);
    }
}