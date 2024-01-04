import java.io.*;
import java.util.*;

public class Main {
    static int[] nums;
    static boolean[] visited;
    static int[] lotto = new int[6];
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String[] input = br.readLine().split(" ");
            if (input[0].equals("0")) {
                break;
            }
            nums = new int[Integer.parseInt(input[0])];
            visited = new boolean[Integer.parseInt(input[0])];
            for (int i = 1; i < input.length; i++) {
                nums[i - 1] = Integer.parseInt(input[i]);
            }
            sb = new StringBuilder();
            generateLotto(0);
            System.out.println(sb.toString());
        }
    }

    static void generateLotto(int cnt) {
        if (cnt == 6) {
            for (int i = 0; i < lotto.length; i++) {
                sb.append(lotto[i]);
                if (i != lotto.length - 1) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!visited[i]) {
                    if (cnt != 0 && lotto[cnt - 1] > nums[i]) {
                        continue;
                    }
                    visited[i] = true;
                    lotto[cnt] = nums[i];
                    generateLotto(cnt + 1);
                    visited[i] = false;
                }
            }
        }
    }
}