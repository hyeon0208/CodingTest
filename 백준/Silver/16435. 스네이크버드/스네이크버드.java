import java.io.*;
import java.util.*;

public class Main {
    private static PriorityQueue<Integer> fruits;
    private static int N;
    private static int L;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        fruits = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits.add(Integer.parseInt(st.nextToken()));
        }
        while (!fruits.isEmpty()) {
            int fruit = fruits.poll();
            if (L < fruit) {
                break;
            }
            L++;
        }
        System.out.println(L);
    }
}