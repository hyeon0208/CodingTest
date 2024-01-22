import java.io.*;
import java.util.*;

public class Main {
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        if (n % 5 == 0) {
            System.out.println(n / 5);
        } else {
            int count = 0;
            boolean flag = false;
            while (n > 0) {
                n -= 2;
                count++;
                if (n % 5 == 0) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                System.out.println(-1);
            } else {
                count += (n / 5);
                System.out.println(count);
            }
        }
    }
}