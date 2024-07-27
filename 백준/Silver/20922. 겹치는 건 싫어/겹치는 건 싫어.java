import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int start = 0;
        int end = 0;
        while (end < N) {
            map.put(numbers[end], map.getOrDefault(numbers[end], 0) + 1);

            while (map.get(numbers[end]) > K) {
                map.put(numbers[start], map.get(numbers[start]) - 1);
                if (map.get(numbers[start]) == 0) {
                    map.remove(numbers[start]);
                }
                start++;
            }

            result = Math.max(result, end - start + 1);
            end++;
        }

        System.out.println(result);
    }
}
