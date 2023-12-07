import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        String s[] = new String[N];
        for (int i = 0; i < N; i++) {
            s[i] = br.readLine();
        }

        int sum = 0;
        String result = "";
        for (int i = 0; i < M; i++) {
            int max = -(int) 1e9;
            int maxIndex = 0;
            int arr[] = new int[4];
            for (int j = 0; j < N; j++) {
                if (s[j].charAt(i) == 'A') {
                    arr[0]++;
                } else if (s[j].charAt(i) == 'C') {
                    arr[1]++;
                } else if (s[j].charAt(i) == 'G') {
                    arr[2]++;
                } else {
                    arr[3]++;
                }
            }
            for (int j = 0; j < 4; j++) {
                if (arr[j] > max) {
                    max = arr[j];
                    maxIndex = j;
                }
            }
            if (maxIndex == 0) {
                result += "A";
            } else if (maxIndex == 1) {
                result += "C";
            } else if (maxIndex == 2) {
                result += "G";
            } else if (maxIndex == 3) {
                result += 'T';
            }
            for (int j = 0; j < 4; j++) {
                if (j != maxIndex) {
                    sum += arr[j];
                }
            }
        }
        System.out.println(result);
        System.out.println(sum);
    }
}