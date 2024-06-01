import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int testCase = 0; testCase < T; testCase++){
            int N = Integer.parseInt(br.readLine());
            int[] rank = new int[N];
            Map<Integer, Integer> result = new HashMap<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++){
                int data = Integer.parseInt(st.nextToken());
                result.put(data, result.getOrDefault(data, 0) + 1);
                rank[i] = data;
            }
            
            int[] fifthGoalIdx = new int[result.size() + 1];
            Map<Integer, Integer> scoreMap = new HashMap<>();
            Map<Integer, Integer> tempMap = new HashMap<>();
            int score = 1;

            for (int element: rank){
                if (result.get(element) >= 6){
                    tempMap.put(element, tempMap.getOrDefault(element, 0) + 1);

                    if (tempMap.get(element) <= 4){
                        scoreMap.put(element, scoreMap.getOrDefault(element, 0) + score);
                    }

                    if (tempMap.get(element) == 5){
                        fifthGoalIdx[element] = score;
                    }
                    score++;
                }
            }
            
            List<Integer> keyData = new ArrayList<>(scoreMap.keySet());
            keyData.sort((x, y) -> {
                if (Objects.equals(scoreMap.get(x), scoreMap.get(y))){
                    return fifthGoalIdx[x] - fifthGoalIdx[y];
                } else{
                    return scoreMap.get(x) - scoreMap.get(y);
                }
            });

            System.out.println(keyData.get(0));
        }
    }
}
