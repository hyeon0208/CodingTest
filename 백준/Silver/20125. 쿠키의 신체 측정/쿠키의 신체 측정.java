import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static boolean[][] map;
    private static int N;
    private static int HEART_X;
    private static int HEART_Y;
    private static int BODY_X;
    private static int BODY_Y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];

        int count = 0;
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                char c = str.charAt(j);
                if (c == '*') {
                    count++;
                    map[i][j] = true;

                    if (count == 1) {
                        HEART_X = i + 1;
                        HEART_Y = j;
                    }
                }
            }
        }

        List<Integer> counts = new ArrayList<>();
        counts.add(getCountByStartIndex(HEART_X, HEART_Y, 0, -1));
        counts.add(getCountByStartIndex(HEART_X, HEART_Y, 0, 1));
        counts.add(getCountByStartIndex(HEART_X, HEART_Y, 1, 0));
        int newStartX = BODY_X;
        int newStartY = BODY_Y;
        counts.add(getCountByStartIndex(newStartX + 1, newStartY - 1, 1, 0) + 2);
        counts.add(getCountByStartIndex(newStartX + 1, newStartY + 1, 1, 0) + 2);

        System.out.println((HEART_X + 1) + " " + (HEART_Y + 1));
        for (Integer i : counts) {
            System.out.print(i + " ");
        }
    }

    private static int getCountByStartIndex(int startX, int startY, int dX, int dY) {
        int count = -1;
        while (true) {
            if (startX < 0 || startX >= N || startY < 0 || startY >= N) {
                break;
            }
            if (!(map[startX][startY])) {
                break;
            }

            startX += dX;
            startY += dY;
            count++;
        }
        BODY_X = startX;
        BODY_Y = startY;

        return count;
    }
}
