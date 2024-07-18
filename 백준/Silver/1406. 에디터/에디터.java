import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        List<Character> strings = new LinkedList<>();
        String str = br.readLine();
        for (char c : str.toCharArray()) {
            strings.add(c);
        }

        ListIterator<Character> cursor = strings.listIterator(strings.size());
        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("P")) {
                cursor.add(st.nextToken().charAt(0));
            } else if (command.equals("L")) {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                }
            } else if (command.equals("D")) {
                if (cursor.hasNext()) {
                    cursor.next();
                }
            } else {
                if (cursor.hasPrevious()) {
                    cursor.previous();
                    cursor.remove();
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character ch : strings) {
            sb.append(ch);
        }

        System.out.println(sb);
    }
}
