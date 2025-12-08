import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        TreeMap<String, Integer> map = new TreeMap<>();
        
        for (int i = 0; i < N; i++) {
            String file = br.readLine();
            String extension = file.split("\\.")[1];
            map.put(extension, map.getOrDefault(extension, 0) + 1);
        }
        
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            sb.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        
        System.out.print(sb);
    }
}