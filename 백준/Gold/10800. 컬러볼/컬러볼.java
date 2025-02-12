import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static List<Ball> balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        balls = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            balls.add(new Ball(i, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        balls.sort(Comparator.comparingInt(b -> b.size));
        // (1, 3), (4, 8), (1, 10), (3, 15) 로 정렬

        // 오름차순 정렬해서 누적합을 적용하면 더 큰공들은 작은 공들이 얻을 수 있는 점수 합만큼 추가로 더해주면 됨.
        int[] result = new int[N];
        int[] colorSum = new int[N + 1]; // 각 색상 별 공들의 크기 합
        int totalSum = 0; // 현재 공보다 작은 모든 공들의 크기 합
        int lastIndex = 0; // 아직 처리하지 않은 가장 작은 공의 위치
        for (int i = 0; i < N; i++) {
            Ball curBall = balls.get(i);
            while (curBall.size > balls.get(lastIndex).size) { // 현재 공보다 작은 크기의 모든 공들을 처리
                totalSum += balls.get(lastIndex).size; // 현재까지 본 모든 작은 공들의 크기를 합산
                colorSum[balls.get(lastIndex).color] += balls.get(lastIndex).size; // 각 색상별로 작은 공들의 크기를 따로 합산
                lastIndex++; // 다음 작은 공으로 이동
            }

            // 현재 공이 실제로 잡을 수 있는 공들의 크기 합 = 현재 공보다 작은 모든 공들의 크기 합 - 현재 공 색상의 합
            result[curBall.index] = totalSum - colorSum[curBall.color];
        }

        StringBuilder sb = new StringBuilder();
        for (int r : result) {
            sb.append(r).append('\n');
        }
        System.out.print(sb);
    }

    private static class Ball {
        int index;
        int color;
        int size;

        public Ball(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }
    }
}
