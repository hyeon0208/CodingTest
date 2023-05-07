import java.util.List;
import java.util.ArrayList;

class Solution {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[2];
        List<String> list = new ArrayList<>();
        
        for(int i = 0; i < words.length; i++){
            // 첫 번째 단어부터 차례대로 확인하면서 이미 있는 단어라면 반복문을 종료하고 answer를 반환한다. 
            if(list.contains(words[i])) {
                // 탈락자의 번호
                answer[0] = (i % n) + 1;
                // 탈락자 자신의 차례
                answer[1] = (i / n) + 1;
                break;
            }
            // 이미 있는 단어가 아니라면 단어를 하나씩 추가한다.
            list.add(words[i]);
            
            // 이전단어의 마지막 문자와 현재단어의 첫 번째 문자가 다르다면 반복문을 종료하고 answer를 반환한다.
            // 첫 번째 단어는 확인할 필요가 없으므로 첫 번째는 제와한다. (제외하지 않을 시 런타임 에러)
            if(i > 0 && words[i - 1].charAt(words[i - 1].length() - 1) != words[i].charAt(0)) {
                answer[0] = (i % n) + 1;
                answer[1] = (i / n) + 1;
                break;
            }
        }
        
        return answer;
    }
}