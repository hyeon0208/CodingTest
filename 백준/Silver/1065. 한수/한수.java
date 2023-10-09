import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());


        System.out.println(getArithmeticSequence(N));
    }

    public static int getArithmeticSequence(int N) {
        int cnt = 0;

        for (int i = 1; i <= N; i++) {
            if (i <= 99) {
                cnt++;
            }
            if (99 < i && i <= 999) {
                String[] numArr = Integer.toString(i).split("");
                if ((Integer.parseInt(numArr[1]) - Integer.parseInt(numArr[0])) == (Integer.parseInt(numArr[2])- Integer.parseInt(numArr[1]))) {
                    cnt += 1;
                }
            }
        }
        return cnt;
    }
}