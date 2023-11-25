import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        Deque<Integer> deck = new ArrayDeque<>();

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();

            if (command.equals("push_front")) {
                int num = Integer.parseInt(st.nextToken());
                deck.addFirst(num);
            } else if (command.equals("push_back")) {
                int num = Integer.parseInt(st.nextToken());
                deck.add(num);
            } else if (command.equals("pop_front")) {
                if (deck.size() == 0)
                    bw.append(-1 + "\n");
                else {
                    int num = deck.poll();
                    bw.append(num + "\n");
                }
            } else if (command.equals("pop_back")) {
                if (deck.size() == 0)
                    bw.append(-1 + "\n");
                else {
                    int num = deck.pollLast();
                    bw.append(num + "\n");
                }
            } else if (command.equals("size"))
                bw.append(deck.size() + "\n");
            else if (command.equals("empty")) {
                if (deck.size() == 0)
                    bw.append(1 + "\n");
                else
                    bw.append(0 + "\n");
            } else if (command.equals("front")) {
                if (deck.size() == 0)
                    bw.append(-1 + "\n");
                else {
                    int num = deck.getFirst();
                    bw.append(num + "\n");
                }
            } else if (command.equals("back")) {
                if (deck.size() == 0)
                    bw.append(-1 + "\n");
                else {
                    int num = deck.getLast();
                    bw.append(num + "\n");
                }
            }
        }
        bw.flush();
        bw.close();
    }
}

