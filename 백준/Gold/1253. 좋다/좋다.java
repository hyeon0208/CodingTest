import java.io.*;
import java.util.*;

public class Main {

    private static int N;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(numbers);

        int result = 0;
        for(int i = 0; i < numbers.length; i++) {
            result += binarySearch(i);
        }
        System.out.println(result);
    }

    private static int binarySearch(int index) {
        int result = 0;

        int target = numbers[index];
        int left = 0;
        int right = N - 1;

        while(left < right) {
            if (left == index) {
                left++;
                continue;
            }
            if (right == index) {
                right--;
                continue;
            }

            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                result++;
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }
}
