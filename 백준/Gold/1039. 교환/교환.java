import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static int n;
    private static int k;
    private static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs();
        System.out.println(result);
    }

    private static void bfs() {
        Queue<Exchange> queue = new LinkedList<>();
        queue.offer(new Exchange(n, 0));
        boolean[][] visited = new boolean[1000001][k + 1];
        visited[n][0] = true;

        while (!queue.isEmpty()) {
            Exchange cur = queue.poll();

            if (cur.count == k) {
                result = Math.max(result, cur.number);
                continue;
            }

            String numStr = String.valueOf(cur.number);
            int m = numStr.length();

            for (int i = 0; i < m - 1; i++) {
                for (int j = i + 1; j < m; j++) {
                    int swapNumber = swap(numStr, i, j);
                    if (swapNumber != -1 && !visited[swapNumber][cur.count + 1]) {
                        queue.offer(new Exchange(swapNumber, cur.count + 1));
                        visited[swapNumber][cur.count + 1] = true;
                    }
                }
            }
        }
    }

    private static int swap(String numStr, int i, int j) {
        char[] charArray = numStr.toCharArray();

        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;

        if (charArray[0] == '0') {
            return -1;
        }

        return Integer.parseInt(String.valueOf(charArray));
    }

    private static class Exchange {
        int number;
        int count;

        public Exchange(int number, int count) {
            this.number = number;
            this.count = count;
        }
    }
}
