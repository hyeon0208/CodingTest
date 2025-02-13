import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] heights = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            heights[i] = Integer.parseInt(st.nextToken());
        }

        int[] count = new int[N + 1];
        int[] nearest = new int[N + 1];
        int[] minDist = new int[N + 1];
        Arrays.fill(minDist, Integer.MAX_VALUE);

        Stack<Integer> stack = new Stack<>(); // 건물 인덱스를 저장

        // i번째 건물이 왼쪽 방향으로 볼 수 있는 고층 건물들이 있는지 탐색
        for (int i = 1; i <= N; i++) {
            // 현재 건물보다 낮거나 같은 건물은 볼 수 없으니 스택에서 제거
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            count[i] = stack.size();

            if (!stack.isEmpty()) {
                // 가장 가까운 건물이 stack의 최상단에 있음.
                minDist[i] = i - stack.peek();
                nearest[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear();

        // i번째 건물이 오른쪽 방향으로 볼 수 있는 고층 건물들이 있는지 탐색
        for (int i = N; i >= 1; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                stack.pop();
            }

            count[i] += stack.size();

            // 현재 구한 거리가 기존에 저장된 최소 거리보다 작을 경우 갱신
            if (!stack.isEmpty() && stack.peek() - i < minDist[i]) {
                minDist[i] = stack.peek() - i;
                nearest[i] = stack.peek();
            }
            stack.push(i);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (count[i] == 0) {
                sb.append(0).append('\n');
            } else {
                sb.append(count[i]).append(" ").append(nearest[i]).append('\n');
            }
        }
        System.out.print(sb);
    }

}
