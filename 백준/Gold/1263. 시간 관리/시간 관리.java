import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        List<Time> times = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int takenTime = Integer.parseInt(st.nextToken()); // 걸리는 시간
            int processTime = Integer.parseInt(st.nextToken()); // 끝내야할 시간
            times.add(new Time(i, takenTime, processTime));
        }

        times.sort(Comparator.comparingInt(time -> time.processTime));

        int totalTime = 0;
        int minStartTime = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            totalTime += times.get(i).takenTime;
            minStartTime = Math.min(minStartTime, times.get(i).processTime - totalTime);

            if (totalTime > times.get(i).processTime) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(minStartTime);
    }


    private static class Time {
        int index;
        int takenTime;
        int processTime;

        public Time(int index, int takenTime, int processTime) {
            this.index = index;
            this.takenTime = takenTime;
            this.processTime = processTime;
        }
    }
}
