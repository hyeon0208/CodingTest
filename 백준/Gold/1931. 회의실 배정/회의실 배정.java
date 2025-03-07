import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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

        meetings.sort(Comparator.comparingInt((Meeting m) -> m.endTime)
                .thenComparingInt(m -> m.startTime));

        int prevEndTime = 0;
        int count = 0;
        for (Meeting meeting : meetings) {
            if (meeting.startTime >= prevEndTime) {
                prevEndTime = meeting.endTime;
                count++;
            }
        }

        System.out.println(count);
    }

    private static class Meeting {
        int startTime;
        int endTime;

        public Meeting(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "startTime=" + startTime +
                    ", endTime=" + endTime +
                    '}';
        }
    }
}
