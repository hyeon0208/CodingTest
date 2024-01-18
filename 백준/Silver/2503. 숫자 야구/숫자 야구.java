import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static boolean[] check = new boolean[1000];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        for (int i = 123; i < 1000; i++) {
            String str = Integer.toString(i);
            if (str.charAt(0) == '0' || str.charAt(1) == '0' || str.charAt(2) == '0') {
                continue;
            }
            if (str.charAt(0) == str.charAt(1) || str.charAt(0) == str.charAt(2) || str.charAt(1) == str.charAt(2)) {
                continue;
            }
            check[i] = true;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int req = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            for (int ans = 123; ans < 1000; ans++) {
                if (check[ans]) {
                    int ns = 0;
                    int nb = 0;
                    for (int first = 0; first < 3; first++) {
                        char r = Integer.toString(req).charAt(first);

                        for (int second = 0; second < 3; second++) {
                            char a = Integer.toString(ans).charAt(second);
                            if (r == a && first == second) {
                                ns++;
                            } else if (r == a && first != second) {
                                nb++;
                            }
                        }
                    }
                    if (ns == s && nb == b) {
                        check[ans] = true;
                    }
                    else {
                        check[ans] = false;
                    }
                }
            }
        }
        int ans = 0;
        for (int i = 123; i < 1000; i++) {
            if (check[i]) {
                ans++;
            }
        }

        System.out.println(ans);
    }
}