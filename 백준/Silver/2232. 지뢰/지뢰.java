import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] trap = new int[N];
        for (int i = 0; i < N; i++) {
            trap[i] = Integer.parseInt(br.readLine());
        }

        if (trap.length == 1) {
            System.out.println("1");
        } else {
            if (trap[0] >= trap[1]) {
                System.out.println(1);
            }
            for (int i = 1; i < N - 1; i++) {
                if (trap[i] >= trap[i - 1] && trap[i] >= trap[i + 1]) {
                    System.out.println(i + 1);
                }
            }
            if (trap[N - 1] >= trap[N - 2]) {
                System.out.println(N);
            }
        }
    }
}
