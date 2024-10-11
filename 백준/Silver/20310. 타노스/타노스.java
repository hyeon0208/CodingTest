import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int zeroCount = 0, oneCount = 0;

        // 0과 1의 개수를 세기
        for (char c : s.toCharArray()) {
            if (c == '0') zeroCount++;
            else oneCount++;
        }

        int removeZero = zeroCount / 2;
        int removeOne = oneCount / 2;

        StringBuilder result = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (c == '0' && removeZero > 0) {
                removeZero--;
            } else if (c == '0') {
                result.append(c);
            }
        }

        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '1' && removeOne > 0) {
                removeOne--;
            } else if (s.charAt(i) == '1') {
                result.append(s.charAt(i));
            }
        }

        System.out.println(result);
    }
}
