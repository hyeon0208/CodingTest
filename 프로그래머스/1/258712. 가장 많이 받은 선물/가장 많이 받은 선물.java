import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        Map<String, Integer> friendsMap = new HashMap<>();
        for(int i = 0; i < friends.length; i++){
            friendsMap.put(friends[i], i);
        }
        
        int[] giftDegree = new int[friends.length];
        int[][] giftGraph = new int[friends.length][friends.length];
        
        for (String gift : gifts) {
            String[] arr = gift.split(" ");
            giftDegree[friendsMap.get(arr[0])]++;
            giftDegree[friendsMap.get(arr[1])]--;
            giftGraph[friendsMap.get(arr[0])][friendsMap.get(arr[1])]++;
        }
                                                      
        int[] res = new int[friends.length];
        for (int i = 0; i < friends.length; i++) {
            for (int j = i + 1; j < friends.length; j++) {
                if (giftGraph[i][j] > giftGraph[j][i]) {
                    res[i]++;
                } else if (giftGraph[i][j] < giftGraph[j][i]) {
                    res[j]++;
                } else {
                    if (giftDegree[i] < giftDegree[j]) {
                        res[j]++;
                    } else if (giftDegree[i] > giftDegree[j]) {
                        res[i]++;
                    }
                }
            }
        }                  
                                                        
        for (int r : res) {
            answer = Math.max(answer, r);
        }
        
        return answer;
    }
}