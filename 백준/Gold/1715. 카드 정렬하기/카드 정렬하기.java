import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> cards = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            cards.add(Integer.valueOf(br.readLine()));
        }

        int result = 0;
        while (!cards.isEmpty()) {
            Integer card = cards.poll();

            if (cards.peek() != null) {
                int sumCard = card + cards.poll();
                result += sumCard;
                cards.offer(sumCard);
            } else {
                break;
            }
        }

        System.out.println(result);

    }
}
