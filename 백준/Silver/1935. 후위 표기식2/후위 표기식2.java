import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String expression = br.readLine();

//        arr = new int[N];
//        for (int i = 0; i < N; i++) {
//            arr[i] = Integer.parseInt(br.readLine());
//        }

        Map<Character, Double> alphas = new HashMap<>();
        for (int i = 0; i < N; i++) {
            double value = Double.parseDouble(br.readLine());
            alphas.put((char)('A' + i), value);  // A부터 순서대로 매핑
        }


        Stack<Double> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isLetter(ch)) { // 문자일 경우
                stack.push(alphas.get(ch));
            } else { // 기호일 경우
                double d1 = stack.pop(); // 기호 바로 앞
                double d2 = stack.pop(); // 기호 앞 앞
                double d3 = 0.0;

                if (ch == '+') {
                    d3 = d2 + d1;
                } else if (ch == '-') {
                    d3 = d2 - d1;
                } else if (ch == '*') {
                    d3 = d2 * d1;
                } else if (ch == '/') {
                    d3 = d2 / d1;
                }

                stack.push(d3);
            }
        }
        System.out.printf("%.2f", stack.pop());
    }
}
