import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] solution(String msg) {
        List<Integer> indexLists = new ArrayList<>();
        Map<String, Integer> book = new HashMap<>();

        char word = 'A';
        for (int i = 1; i <= ('Z' - 'A') + 1; i++) {
            book.put(word + "", i);
            word += 1;
        }

        int idx = 0;
        while(idx < msg.length()){
            String w = "";
            while(idx < msg.length()){
                if(!book.containsKey(w+msg.charAt(idx))){
                    break;
                }else{
                    w += msg.charAt(idx);
                }
                idx++;
            }

            indexLists.add(book.get(w));
            if(idx < msg.length()){
                book.put(w+msg.charAt(idx), book.size() + 1);
            }
        }
        return indexLists.stream().mapToInt(Integer::intValue).toArray();
    }
}