import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String rememberedMelody = convertSharp(m);

        String answer = "(None)";
        int maxPlayTime = 0;

        for (String info : musicinfos) {
            String[] parts = info.split(",");
            String startTime = parts[0];
            String endTime = parts[1];
            String title = parts[2];
            String melody = parts[3];

            // 재생 시간 계산 (분 단위)
            int playTime = calculatePlayTime(startTime, endTime);

            // # 음표 변환
            String convertedMelody = convertSharp(melody);

            // 재생 시간에 맞춰 실제 재생된 멜로디 생성
            String playedMelody = generatePlayedMelody(convertedMelody, playTime);

            // 기억한 멜로디가 재생된 멜로디에 포함되는지 확인
            if (playedMelody.contains(rememberedMelody)) {
                // 재생 시간이 더 길거나, 같으면 먼저 입력된 곡(현재 answer가 비어있음)
                if (playTime > maxPlayTime) {
                    maxPlayTime = playTime;
                    answer = title;
                }
            }
        }

        return answer;
    }

    private String convertSharp(String melody) {
        return melody.replace("C#", "c")
                     .replace("D#", "d")
                     .replace("F#", "f")
                     .replace("G#", "g")
                     .replace("A#", "a")
                    .replace("B#", "b")
                    .replace("E#", "e");
    }

    private int calculatePlayTime(String start, String end) {
        String[] startParts = start.split(":");
        String[] endParts = end.split(":");

        int startMinutes = Integer.parseInt(startParts[0]) * 60 + Integer.parseInt(startParts[1]);
        int endMinutes = Integer.parseInt(endParts[0]) * 60 + Integer.parseInt(endParts[1]);

        return endMinutes - startMinutes;
    }

    private String generatePlayedMelody(String melody, int playTime) {
        StringBuilder played = new StringBuilder();
        int melodyLength = melody.length();

        for (int i = 0; i < playTime; i++) {
            played.append(melody.charAt(i % melodyLength));
        }

        return played.toString();
    }
}