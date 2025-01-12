import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.swing.text.Position;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Queue<Person> queue = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            queue.offer(new Person(Integer.parseInt(st.nextToken()), i, 0, 0));
        }

        List<Person> personList = new ArrayList<>(N);

        int time = 1;
        while (!queue.isEmpty()) {
            Person cur = queue.poll();
            cur.takePizza();
            if (!cur.isFull()) {
                queue.offer(new Person(cur.needs, cur.order, cur.count, time));
            } else {
                personList.add(new Person(cur.needs, cur.order, cur.count, time));
            }
            time++;
        }

        personList.sort(Comparator.comparingInt(value -> value.order));
        String collect = personList.stream()
                .map(person -> person.time)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(collect);
    }

    private static class Person {
        private final int needs;
        private int order;
        private int count;
        private int time;

        public Person(int needs, int order, int count, int time) {
            this.needs = needs;
            this.order = order;
            this.count = count;
            this.time = time;
        }

        public void takePizza() {
            this.count++;
            this.time++;
        }

        public boolean isFull() {
            return needs == count;
        }

        public int getNeeds() {
            return needs;
        }

        public int getCount() {
            return count;
        }
    }
}
