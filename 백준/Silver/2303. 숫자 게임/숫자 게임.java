import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] maxNumbers = new int[N];
        String[][] arr = new String[N][5];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            arr[i] = str.split(" ");
            int sum = 0;
            int temp;
            for (int j = 0; j < 3; j++) {
                for (int k = j + 1; k < 4; k++) {
                    for (int l = k + 1; l < 5; l++) {
                        temp = Integer.parseInt(arr[i][j]) + Integer.parseInt(arr[i][k]) + Integer.parseInt(arr[i][l]);
                        if (sum <= temp % 10) {
                            sum = temp % 10;
                        }
                    }
                }
            }
            maxNumbers[i] = sum;
        }
        int maxIndex = 0;
        int result = maxNumbers[0];
        for (int i = 0; i < maxNumbers.length; i++) {
            if (maxNumbers[i] >= result) {
                result = maxNumbers[i];
                maxIndex = i + 1;
            }
        }
        System.out.println(maxIndex);
    }
}