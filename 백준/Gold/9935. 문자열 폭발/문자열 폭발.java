import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        String bomb = br.readLine();

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : str.toCharArray()) {
            deque.push(c);

            if (deque.size() >= bomb.length()) {
                boolean isBomb = true;

                Iterator<Character> iter = deque.iterator();
                for (int i = bomb.length() - 1; i >= 0; i--) {
                    if (iter.next() != bomb.charAt(i)) {
                        isBomb = false;
                        break;
                    }
                }
                if (isBomb) {
                    for (int i = 0; i < bomb.length(); i++) {
                        deque.pop();
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!deque.isEmpty()) {
            sb.append(deque.pollLast());
        }

        System.out.println(sb.length() > 0 ? sb : "FRULA");
    }
}
