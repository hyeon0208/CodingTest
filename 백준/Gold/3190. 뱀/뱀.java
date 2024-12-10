import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static int[] DX = {0, 1, 0, -1};
    private static int[] DY = {1, 0, -1, 0};

    private static int N;
    private static int K;
    private static int L;
    private static Map<Integer, Character> directions = new HashMap<>();

    private static int[][] maps;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        maps = new int[N][N];

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            maps[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char direction = st.nextToken().charAt(0);
            directions.put(time, direction);
        }

        System.out.println(simulate());
    }

    private static int simulate() {
        Deque<int[]> snake = new ArrayDeque<>();
        snake.offer(new int[]{0, 0});

        int time = 0;
        int direction = 0; // 처음에는 오른쪽을 향함

        while (true) {
            time++;

            // 뱀의 머리를 다음 위치로 이동
            int[] head = snake.peekFirst();
            int nx = head[0] + DX[direction];
            int ny = head[1] + DY[direction];

            // 벽에 부딪히거나 자기자신과 부딪힌 경우
            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                break;
            }

            // 자신의 몸과 충돌한 경우
            boolean collision = false;
            for (int[] body : snake) {
                if (body[0] == nx && body[1] == ny) {
                    collision = true;
                    break;
                }
            }
            if (collision) {
                break;
            }

            // 사과가 있는 경우
            if (maps[nx][ny] == 1) {
                maps[nx][ny] = 0;
            } else {
                snake.pollLast(); // 꼬리 제거
            }

            // 새로운 머리 추가
            snake.offerFirst(new int[]{nx, ny});

            // 방향 전환 체크
            if (directions.containsKey(time)) {
                if (directions.get(time) == 'L') {
                    direction = (direction + 3) % 4; // 왼쪽 이동
                } else {
                    direction = (direction + 1) % 4; // 오른쪽 이동
                }
            }
        }

        return time;
    }
}
