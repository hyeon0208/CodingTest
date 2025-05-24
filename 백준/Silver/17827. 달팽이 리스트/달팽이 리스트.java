import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        
        int[] values = new int[N + 1]; // 1-indexed
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            values[i] = Integer.parseInt(st.nextToken());
        }
        
        StringBuilder sb = new StringBuilder();
        
        int cycleLength = N - V + 1;
        
        for (int i = 0; i < M; i++) {
            long K = Long.parseLong(br.readLine());
            
            long target = K + 1;
            
            int result;
            if (target <= N) {
                result = values[(int)target];
            } else {
                long excess = target - N; 
                int cyclePosition = (int)((excess - 1) % cycleLength); 
                
                result = values[V + cyclePosition];
            }
            
            sb.append(result).append('\n');
        }
        
        System.out.print(sb);
    }
}