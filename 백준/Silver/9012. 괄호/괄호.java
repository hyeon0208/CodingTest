import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            Stack<Character> stack = new Stack<>();
            String data = br.readLine();

            for (int j = 0; j < data.length(); j++) {
                char ch = data.charAt(j);

                if (ch == '(') {
                    stack.push(ch);
                }
                if (ch == ')') {
                    if (stack.size() == 0) {
                        stack.push(ch);
                        break;
                    }
                    if (stack.size() != 0) {
                        stack.pop();
                    }
                }
            }
            if (stack.isEmpty()) {
                System.out.println("YES");
            }
            if (!stack.isEmpty()) {
                System.out.println("NO");
            }
        }
    }
}

