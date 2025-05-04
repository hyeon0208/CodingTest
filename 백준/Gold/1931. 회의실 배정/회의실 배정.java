import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Meeting> meetings = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            meetings.add(new Meeting(startTime, endTime));
        }

        meetings.sort(Comparator.comparingInt(Meeting::getEnd)
                .thenComparing((o1, o2) -> o1.start - o2.start));

        int result = 0;
        int prevEnd = 0;
        for (int i = 0; i < meetings.size(); i++) {
            if (prevEnd <= meetings.get(i).start) {
                result++;
                prevEnd = meetings.get(i).end;
            }
        }

        System.out.println(result);
    }

    private static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int getEnd() {
            return end;
        }
    }
}
