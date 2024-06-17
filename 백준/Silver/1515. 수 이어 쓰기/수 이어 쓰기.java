import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        char[] arr = input.toCharArray();
        int pointer = 0;

        int result = 1;

        while (pointer < arr.length) {
            String num = Integer.toString(result);
            for (int i = 0; i < num.length(); i++) {
                if (num.charAt(i) == arr[pointer]) {
                    pointer++;
                }
                if (pointer >= arr.length) {
                    break;
                }
            }
            result++;
        }
        System.out.println(result - 1);
    }
}
