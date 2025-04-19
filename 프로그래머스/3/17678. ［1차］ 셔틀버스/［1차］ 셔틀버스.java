import java.util.*;

class Solution {
    public static String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crewTimes = new PriorityQueue<>();
        for (String s : timetable) {
            String[] split = s.split(":");
            int hour = Integer.parseInt(split[0]) * 60;
            int minutes = Integer.parseInt(split[1]);
            crewTimes.offer(hour + minutes);
        }

        int startTime = 540;
        int cornTime = 0;
        for (int i = 1; i <= n; i++) {
            int count = 0;
            while (!crewTimes.isEmpty() && crewTimes.peek() <= startTime && count < m) {
                int crewTime = crewTimes.poll();
                count++;
                // 마지막 버스이고, 정원이 다 찰 경우 콘은 마지막 크루보다 1분 먼저 와야 함
                if (i == n && count == m) {
                    cornTime = crewTime - 1;
                }
            }
            // 마지막 버스이고, 자리가 남았다면 버스 도착 시간에 맞춰서 타면 됨
            if (i == n && count < m) {
                cornTime = startTime;
            }
            startTime += t;
        }

        return String.format("%02d:%02d", cornTime / 60, cornTime % 60);
    }
}