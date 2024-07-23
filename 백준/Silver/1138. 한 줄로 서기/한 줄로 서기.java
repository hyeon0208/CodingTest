import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] leftCounts = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            leftCounts[i] = Integer.parseInt(st.nextToken());
        }
        
        List<Integer> result = new ArrayList<>();
        for (int i = N - 1; i >= 0; i--) {
            result.add(leftCounts[i], i + 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int num : result) {
            sb.append(num).append(" ");
        }

        System.out.println(sb);
    }
}
