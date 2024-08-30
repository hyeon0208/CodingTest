import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[] tops;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        tops = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tops[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        Stack<Top> stack = new Stack<>();
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty()) {
                if (stack.peek().height >= tops[i]) {
                    sb.append(stack.peek().index + 1).append(" ");
                    break;
                }
                stack.pop();
            }

            if (stack.isEmpty()) {
                sb.append(0).append(" ");
            }
            stack.push(new Top(tops[i], i));
        }
        System.out.println(sb);
    }


    private static class Top {
        int height;
        int index;

        public Top(int height, int index) {
            this.height = height;
            this.index = index;
        }
    }
}
