import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    private static int[][] gears = new int[4][8];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                gears[i][j] = line.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gearNum = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            // 회전 전 각 톱니바퀴의 회전 방향을 결정
            int[] rotations = new int[4];
            rotations[gearNum] = direction;

            // 왼쪽 톱니바퀴들 확인
            for (int j = gearNum; j > 0; j--) {
                if (gears[j][6] != gears[j - 1][2]) {  // 맞닿은 극이 다르면
                    rotations[j - 1] = -rotations[j];  // 반대 방향 회전
                } else {
                    break;
                }
            }

            // 오른쪽 톱니바퀴들 확인
            for (int j = gearNum; j < 3; j++) {
                if (gears[j][2] != gears[j + 1][6]) {  // 맞닿은 극이 다르면
                    rotations[j + 1] = -rotations[j];  // 반대 방향 회전
                } else {
                    break;
                }
            }

            // 모든 톱니바퀴 회전
            for (int j = 0; j < 4; j++) {
                if (rotations[j] != 0) { // 0이면 회전하지 않음
                    rotate(j, rotations[j]);
                }
            }
        }

        System.out.println(calculateScore(gears));
    }

    private static void rotate(int gearNum, int direction) {
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < 8; i++) {
            deque.add(gears[gearNum][i]);
        }

        if(direction == 1) { // 시계 방향
            deque.addFirst(deque.removeLast());
        } else { // 반시계 방향
            deque.addLast(deque.removeFirst());
        }

        for(int i = 0; i < 8; i++) {
            gears[gearNum][i] = deque.pollFirst();
        }
    }

    private static int calculateScore(int[][] gears) {
        int score = 0;
        for (int i = 0; i < 4; i++) {
            if (gears[i][0] == 1) {
                score += (int) Math.pow(2, i);
            }
        }
        return score;
    }
}
