import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Queue<Integer> q = new LinkedList();
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        while (q.size() != 1) {
            int discardedCard = q.poll();
            System.out.print(discardedCard + " ");
            int topCard = q.poll();
            q.add(topCard);
        }
        System.out.print(q.poll());
    }
}