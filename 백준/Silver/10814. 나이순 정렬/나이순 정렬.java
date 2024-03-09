import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<Person> people;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        people = new ArrayList<>(N);
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int age = Integer.parseInt(st.nextToken());
            String name = st.nextToken();
            people.add(new Person(age, name));
        }

        Collections.sort(people, (o1, o2) -> o1.age - o2.age);

        StringBuilder builder = new StringBuilder();
        for (Person person : people) {
            builder.append(String.join(" ", String.valueOf(person.age), person.name))
                    .append(System.lineSeparator());
        }
        System.out.println(builder);
    }

    static class Person {
        int age;
        String name;

        public Person(final int age, final String name) {
            this.age = age;
            this.name = name;


        }
    }
}
