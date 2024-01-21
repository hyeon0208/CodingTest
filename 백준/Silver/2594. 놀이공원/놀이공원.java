import java.io.*;
import java.util.*;

public class Main {
    static int N, max, endMax;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][2];
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            arr[i][0] = (start / 100) * 60 + (start % 100) - 10;
            arr[i][1] = (end / 100) * 60 + (end % 100) + 10;
        }
        Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
        max = Math.max(max, arr[0][0] - 600);

        for(int i = 1; i < N; i++) {
            endMax = Math.max(endMax, arr[i-1][1]);
            if(arr[i][0] > endMax) {
                max = Math.max(max, arr[i][0] - endMax);
            }
        }

        Arrays.sort(arr, Comparator.comparingInt(o -> o[1]));
        max = Math.max(max,  1320 - arr[N-1][1]);

        System.out.println(max);
    }
}
