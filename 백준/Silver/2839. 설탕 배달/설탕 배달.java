import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static int res = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        int fiveDiv = N / 5;
        int threeDiv = 0;

        while (fiveDiv >= 0) {
            int remain = N - (fiveDiv * 5);
            if (remain % 3 == 0) {
                threeDiv = remain / 3;
                res = fiveDiv + threeDiv;
                break;
            }
            fiveDiv--;
        }
        System.out.println(res);
    }
}