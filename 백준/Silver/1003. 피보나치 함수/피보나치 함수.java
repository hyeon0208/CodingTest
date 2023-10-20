import java.io.*;

public class Main {
    static int zeroCount = 0;
    static int oneCount = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            fibonacci(N);
            sb.append(zeroCount).append(" ").append(oneCount).append("\n");
        }
        System.out.println(sb);
    }

    public static void fibonacci(int N) {
        zeroCount = 1;
        oneCount = 0;
        int zeroPlusOne = 1;

        for (int i = 0; i < N; i++) {
            zeroCount = oneCount;
            oneCount = zeroPlusOne;
            zeroPlusOne = zeroCount + oneCount;
        }
    }
}



