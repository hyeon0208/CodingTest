import java.io.*;
import java.util.*;

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int setPrice = 1000;
        int unitPrice = 1000;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            setPrice = Math.min(setPrice, Integer.parseInt(st.nextToken()));
            unitPrice = Math.min(unitPrice, Integer.parseInt(st.nextToken()));
        }
        if (unitPrice * 6 < setPrice) {
            System.out.println(unitPrice * N);
        } else {
            int result = (setPrice * (N / 6)) + Math.min(N % 6 * unitPrice, setPrice);
            System.out.println(result);
        }
    }
}