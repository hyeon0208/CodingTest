import java.util.*;

class Solution {

    private static int[] times;
    
    public static String solution(String play_time, String adv_time, String[] logs) {
        int playTime = Time.toSeconds(play_time);
        int advTime = Time.toSeconds(adv_time);
        times = new int[playTime + 1];

        for (String log : logs) {
            Time time = Time.createTime(log);
            times[time.startSec]++;
            times[time.endSec]--;
        }

        for (int i = 1; i <= playTime; i++) {
            times[i] += times[i - 1];
        }
        
        long maxViewTime = 0;
        int maxStartTime = 0;
        for (int start = 0; start <= playTime - advTime; start++) {
            long totalViewTime = 0;

            for (int i = start; i < start + advTime; i++) {
                totalViewTime += times[i];
            }

            if (totalViewTime > maxViewTime) {
                maxViewTime = totalViewTime;
                maxStartTime = start;
            }
        }

        return convertToTimeFormat(maxStartTime);
    }

    private static String convertToTimeFormat(int seconds) {
        int hour = seconds / 3600;
        int minute = (seconds % 3600) / 60;
        int second = seconds % 60;
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    private static class Time {
        int startSec;
        int endSec;

        private Time(int startSec, int endSec) {
            this.startSec = startSec;
            this.endSec = endSec;
        }

        public static Time createTime(String log) {
            String[] startAndEnd = log.split("-");
            String startLog = startAndEnd[0];
            String endLog = startAndEnd[1];

            return new Time(toSeconds(startLog), toSeconds(endLog));
        }

        public static int toSeconds(String time) {
            String[] arr = time.split(":");
            int hour = Integer.parseInt(arr[0]);
            int minutes = Integer.parseInt(arr[1]);
            int second = Integer.parseInt(arr[2]);
            return (hour * 3600) + (minutes * 60) + second;
        }
    }
}