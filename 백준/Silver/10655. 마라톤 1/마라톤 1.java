import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int x;
    private static int y;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<Position> positions = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            positions.add(new Position(x, y));
        }

        // 모든 곳을 방문했을 대의 거리를 구한 뒤 최대한 절약할 수 있는 한 지점을 구해 뺀다.
        int allDistance = 0;  // 모든 체크포인트를 방문하는 거리
        int maxSkipSaveDistance = 0;    // 한 지점을 건너뛰어서 절약할 수 있는 최대 거리
        for (int i = 1; i < N; i++) {
            int curDistance = getDistance(positions.get(i), positions.get(i - 1));
            allDistance += curDistance;

            if (i < N - 1) {
                // 건너뛰지 않고 가는 방식 : 현재 이동거리 + 다음 이동거리
                int noSkipDistance = curDistance + getDistance(positions.get(i), positions.get(i + 1));

                // 건너뛰는 방식
                int skipDistance = getDistance(positions.get(i - 1), positions.get(i + 1));

                // 현재 지점을 건너뛰었을 때 절약되는 거리 계산
                int currentSaving = noSkipDistance - skipDistance;
                maxSkipSaveDistance = Math.max(maxSkipSaveDistance, currentSaving);
            }
        }

        System.out.println(allDistance - maxSkipSaveDistance);
    }


    static int getDistance(Position p1, Position p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    private static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
