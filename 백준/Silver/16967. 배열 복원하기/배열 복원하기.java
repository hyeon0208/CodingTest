import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        
        int[][] arrB = new int[H + X][W + Y];
        int[][] arrA = new int[H][W];

        for (int i = 0; i < H + X; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W + Y; j++) {
                arrB[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                if(i < X || j < Y) {
                    // 겹치지 않는 부분은 B배열 값을 그대로 가져옴
                    arrA[i][j] = arrB[i][j];
                } else {
                    // 겹치는 부분은 B배열 값에서 이미 구한 A배열 값을 빼줌
                    arrA[i][j] = arrB[i][j] - arrA[i - X][j - Y];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                sb.append(arrA[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
