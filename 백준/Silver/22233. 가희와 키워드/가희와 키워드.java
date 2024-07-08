import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> keywords = new HashSet<>(N);
        for (int i = 0; i < N; i++) {
            keywords.add(br.readLine());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] splitWords = br.readLine().split(",");
            for (String splitWord : splitWords) {
                keywords.remove(splitWord);
            }
            sb.append(keywords.size()).append("\n");
        }

        System.out.println(sb);
    }
}
