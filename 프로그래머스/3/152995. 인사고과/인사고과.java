import java.util.*;

class Solution {
    
    public static int solution(int[][] scores) {
        int[] wanho = scores[0];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i][0] > wanho[0] && scores[i][1] > wanho[1]) {
                return -1;
            }
        }

        Arrays.sort(scores, (a, b) -> {
            if (a[0] != b[0]) {
                return b[0] - a[0]; // 근무태도(첫 번째 점수) 내림차순
            }
            return a[1] - b[1]; // 성과(두 번째 점수) 오름차순
        });

        List<Person> eligible = new ArrayList<>();
        int maxPerformance = 0;
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][1] >= maxPerformance) {
                eligible.add(new Person(i, scores[i][0], scores[i][1], scores[i][0] + scores[i][1]));
                maxPerformance = scores[i][1];
            }
        }

        eligible.sort((a, b) -> b.getTotalScore() - a.getTotalScore());

        int rank = 1;
        int prevScore = -1;
        for (int i = 0; i < eligible.size(); i++) {
            Person current = eligible.get(i);
            int totalScore = current.totalScore;

            if (totalScore != prevScore) {
                rank = i + 1;
                prevScore = totalScore;
            }

            if (current.score1 == wanho[0] && current.score2 == wanho[1]) {
                return rank;
            }
        }

        return -1;
    }

    private static class Person {
        int index;
        int score1;
        int score2;
        int totalScore;

        public Person(int index, int score1, int score2, int totalScore) {
            this.index = index;
            this.score1 = score1;
            this.score2 = score2;
            this.totalScore = totalScore;
        }

        public int getTotalScore() {
            return totalScore;
        }
    }
}