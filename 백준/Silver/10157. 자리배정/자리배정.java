import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static final int[] DX = {0, 1, 0, -1};
    private static final int[] DY = {1, 0, -1, 0};
    
    private static int C;
    private static int R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        if (K > C * R) {
            System.out.println(0);
            return;
        }

        boolean[][] visited = new boolean[C][R];
        int curX = 0;
        int curY = 0;
        int num = 1;
        int dir = 0;
        while (num < K) {
            visited[curX][curY] = true;

            int nx = curX + DX[dir];
            int ny = curY + DY[dir];
            if (!isValidRange(nx, ny) || visited[nx][ny]) {
                dir = (dir + 1) % 4;
                nx = curX + DX[dir];
                ny = curY + DY[dir];
            }
            curX = nx;
            curY = ny;
            num++;
        }

        System.out.println((curX + 1) + " " + (curY + 1));
    }

    private static boolean isValidRange(int x, int y) {
        return 0 <= x && x < C && 0 <= y && y < R;
    }
}
