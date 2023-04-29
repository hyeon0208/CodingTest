import java.util.HashMap;
import java.util.Map;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        Map<String, Integer> player = new HashMap<>();

        for (String name : participant) {
            player.put(name, player.getOrDefault(name, 0) + 1);
        }

        for (String name : completion) {
            player.put(name, player.getOrDefault(name, 0) - 1);
        }
        
        for (String name : player.keySet()) {
            if (player.get(name) == 1) {
                answer = name;
                break;
            }
        }

        return answer;
    }
}