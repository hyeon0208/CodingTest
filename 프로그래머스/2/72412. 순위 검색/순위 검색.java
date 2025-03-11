import java.util.*;

class Solution {
    
    private static final String ASTERISK = "-";
    
    public static int[] solution(String[] info, String[] query) {
        Map<String, List<Integer>> map = new HashMap<>();

        // 모든 지원자 정보를 전처리하여 가능한 모든 키 조합 생성
        for (String s : info) {
            String[] split = s.split(" ");
            String language = split[0];
            String occupation = split[1];
            String history = split[2];
            String food = split[3];
            int score = Integer.parseInt(split[4]);

            // 모든 조합 생성 (2^4 = 16개)
            generateCombinations(map, language, occupation, history, food, score, 0, new String[4]);
        }

        // 이진 탐색을 위해 모든 점수 리스트 정렬
        for (List<Integer> scores : map.values()) {
            Collections.sort(scores);
        }

        int[] answer = new int[query.length];
        for (int i = 0; i < query.length; i++) {
            String[] split = query[i].split(" and ");
            String[] lastPart = split[3].split(" ");
            String language = split[0];
            String occupation = split[1];
            String history = split[2];
            String food = lastPart[0];
            int minScore = Integer.parseInt(lastPart[1]);

            String key = language + "," + occupation + "," + history + "," + food;

            // 해당 키에 대한 점수 리스트 조회 및 이진 탐색
            if (map.containsKey(key)) {
                List<Integer> scores = map.get(key);
                answer[i] = countGreaterOrEqual(scores, minScore);
            }
        }

        return answer;
    }

    // 지원자 정보로 가능한 모든 조합 생성
    private static void generateCombinations(Map<String, List<Integer>> map,
                                             String language, String occupation,
                                             String history, String food, int score,
                                             int index, String[] current) {
        if (index == 4) {
            String key = String.join(",", current);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(score);
            return;
        }

        String value = "";
        if (index == 0) {
            value = language;
        } else if (index == 1) {
            value = occupation;
        } else if (index == 2) {
            value = history;
        } else if (index == 3) {
            value = food;
        }

        current[index] = value;
        generateCombinations(map, language, occupation, history, food, score, index + 1, current);

        current[index] = ASTERISK;
        generateCombinations(map, language, occupation, history, food, score, index + 1, current);
    }

    // 이진 탐색을 사용하여 기준 점수 이상인 항목 수 계산
    private static int countGreaterOrEqual(List<Integer> scores, int target) {
        int left = 0;
        int right = scores.size();

        while (left < right) {
            int mid = (right + left) / 2;
            if (scores.get(mid) >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return scores.size() - left;
    }
}