import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] DX = {1, -1, 0, 0};
    private static final int[] DY = {0, 0, -1, 1};

    private static int N;
    private static int K;
    private static int S;
    private static int X;
    private static int Y;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N][N];
        PriorityQueue<Virus> pq = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time) {
                return a.type - b.type;
            }
            return a.time - b.time;
        });
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    pq.offer(new Virus(i, j, arr[i][j], 0));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken()) - 1;
        Y = Integer.parseInt(st.nextToken()) - 1;
        while (!pq.isEmpty()) {
            Virus curr = pq.poll();
            if (curr.time == S) {
                break;
            }
            for (int i = 0; i < 4; i++) {
                int nx = curr.x + DX[i];
                int ny = curr.y + DY[i];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N && arr[nx][ny] == 0) {
                    arr[nx][ny] = curr.type;
                    pq.offer(new Virus(nx, ny, curr.type, curr.time + 1));
                }
            }
        }

        System.out.println(arr[X][Y]);
    }

    private static class Virus {
        int x;
        int y;
        int type;
        int time;

        Virus(int x, int y, int type, int time) {
            this.x = x;
            this.y = y;
            this.type = type;
            this.time = time;
        }
    }
}
