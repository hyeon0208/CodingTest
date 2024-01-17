import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Queue<Integer> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        q = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        int index = 1;
        while (q.size() != 1) {
            long pow = (long) (Math.pow(index, 3) - 1);
            long mod = pow % q.size();
            if (mod < 0) {
                mod += q.size();
            }
            for (int i = 0; i < mod; i++) {
                q.add(q.poll());
            }
            q.poll();
            index++;
        }

        System.out.println(q.peek());
    }
}