import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String findWord = br.readLine();

        int result = 0;
        int startIndex = 0;
        while (startIndex <= origin.length() - findWord.length()) {
            if (findWord.equals(origin.substring(startIndex, startIndex + findWord.length()))) {
                result++;
                startIndex = startIndex + findWord.length();
            } else {
                startIndex++;
            }
        }

        System.out.println(result);
    }
}
