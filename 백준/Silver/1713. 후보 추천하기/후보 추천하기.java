import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, total;
        Student students[] = new Student[101];
        List<Student> list = new ArrayList<>();
        N = Integer.parseInt(br.readLine());
        total = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int num;
        for (int i = 0; i < total; i++) {
            num = Integer.parseInt(st.nextToken());
            if (students[num] == null) {
                students[num] = new Student(num, 0, 0, false);
            }
            if (students[num].posted) {
                students[num].likeCount++;
            } else {
                if (list.size() == N) {
                    Collections.sort(list, (o1, o2) -> {
                        if (o1.likeCount == o2.likeCount) {
                            return o1.time - o2.time;
                        }
                        return o1.likeCount - o2.likeCount;
                    });
                    list.get(0).posted = false;
                    list.remove(0);
                }
                students[num].likeCount = 1;
                students[num].time = i;
                students[num].posted = true;
                list.add(students[num]);
            }
        }

        Collections.sort(list, Comparator.comparingInt(o -> o.id));
        StringBuilder sb = new StringBuilder();
        for (Student student : list) {
            sb.append(student.id)
                    .append(" ");
        }
        System.out.println(sb);
    }

    static class Student {
        int id;
        int likeCount;
        int time;
        boolean posted;

        public Student(int id, int likeCount, int time, boolean posted) {
            this.id = id;
            this.likeCount = likeCount;
            this.time = time;
            this.posted = posted;
        }
    }
}