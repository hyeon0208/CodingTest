import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> {
            int A = Math.abs(o1);
            int B = Math.abs(o2);

            if(A == B) {
               return o1 > o2 ? 1 : -1;
            }
            return A - B;
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(br.readLine());
            if (x == 0) {
                if (!queue.isEmpty()) {
                    sb.append(queue.poll()).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            } else queue.add(x);
        }
        System.out.println(sb);
    }
}