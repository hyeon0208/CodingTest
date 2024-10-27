import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 갖고 있는 랜선 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선 개수

        long[] renLengths = new long[K];

        for (int i = 0; i < K; i++) {
            renLengths[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(renLengths);

        long start = 1;
        long end = renLengths[K - 1];
        long result = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            long count = 0;


            for(int i = 0; i < K; i++) {
                count += renLengths[i] / mid;
            }

            if (count < N) {
                end = mid - 1;
            } else {
                result = mid;
                start = mid + 1;
            }
        }

        System.out.println(result);
    }
}