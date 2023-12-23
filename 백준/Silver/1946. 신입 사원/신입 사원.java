import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            List<Employee> employees = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                employees.add(new Employee(a, b));
            }
            Collections.sort(employees);

            int ans = 1;
            int min = employees.get(0).b;
            for (int i = 1; i < N; i++) {
                if (employees.get(i).b < min) {
                    ans++;
                    min = employees.get(i).b;
                }
            }
            System.out.println(ans);
        }
    }
}

class Employee implements Comparable<Employee> {
    int a;
    int b;

    Employee(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int compareTo(Employee o) {
        if (this.a > o.a) {
            return 1;
        } else {
            return -1;
        }
    }
}