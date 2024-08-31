import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int H;
    private static int W;
    private static int[] blocks;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int result = 0;
        // 첫 번째와 마지막 인덱스는 빗물이 쌓일 수 없다.
        for (int i = 1; i < W - 1; i++) {
            int currentHeight = blocks[i];
            int leftHeight = 0;
            int rightHeight = 0;
            for (int j = i - 1; j >= 0; j--) {
                int nextHeight = blocks[j];
                if (nextHeight > currentHeight) {
                    leftHeight = Math.max(leftHeight, nextHeight);
                }
            }
            for (int j = i + 1; j < W; j++) {
                int nextHeight = blocks[j];
                if (currentHeight < nextHeight) {
                    rightHeight = Math.max(rightHeight, nextHeight);
                }
            }
            if (Math.min(leftHeight, rightHeight) > currentHeight) {
                result += Math.min(leftHeight, rightHeight) - currentHeight;
            }
        }
        System.out.println(result);
    }
}
