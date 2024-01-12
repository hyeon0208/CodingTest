import java.io.*;
import java.util.*;

public class Main {
    static int[][] arr = new int[100][100];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 4; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            init(x1,y1,x2,y2);
        }
        System.out.println(calculate());
    }

    public static void init(int x1, int y1, int x2, int y2) {
        for(int i = x1; i < x2; i++) {
            for(int j = y1; j < y2; j++) {
                arr[i][j] = 1;
            }
        }
    }

    public static int calculate() {
        int sum = 0;
        for(int i = 0; i < 100; i++) {
            for(int j = 0; j < 100; j++) {
                sum += arr[i][j];
            }
        }
        return sum;
    }
}