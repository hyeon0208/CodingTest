import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static long X;
    private static long Y;
    private static long W;
    private static long S;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        // Case 1: 모든 이동을 직선으로 하는 경우
        long straightOnly = (X + Y) * W;

        // Case 2: 가능한 한 대각선으로 이동하고 나머지는 직선으로 이동
        long diagonal = 0;
        long min = Math.min(X, Y);
        long diff = Math.abs(X - Y);

        // 대각선으로 이동할 수 있는 만큼 이동
        diagonal = min * S;

        // 남은 거리는 직선으로 이동
        diagonal += diff * W;

        // Case 3: 대각선을 지그재그로 이동하는 경우
        long zigzag = 0;
        if ((X + Y) % 2 == 0) {
            zigzag = Math.max(X, Y) * S;
        } else {
            zigzag = (Math.max(X, Y) - 1) * S + W;
        }

        // 세 가지 경우 중 최소값 선택
        long result = Math.min(straightOnly, Math.min(diagonal, zigzag));

        System.out.println(result);
    }
}
