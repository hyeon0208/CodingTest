import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int m = Integer.parseInt(br.readLine());
        
        long sum = 0;
        long xor = 0; 
        
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int operation = Integer.parseInt(st.nextToken());
            
            if (operation == 1) {
                int x = Integer.parseInt(st.nextToken());
                sum += x;
                xor ^= x;
            } else if (operation == 2) {
                int y = Integer.parseInt(st.nextToken());
                sum -= y;
                xor ^= y;
            } else if (operation == 3) {
                sb.append(sum).append('\n');
            } else if (operation == 4) {
                sb.append(xor).append('\n');
            }
        }
        
        System.out.print(sb);
    }
}