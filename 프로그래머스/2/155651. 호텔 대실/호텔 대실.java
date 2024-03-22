import java.util.*;

class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        int[][] bookTimes = new int[book_time.length][2];
        
        for (int i = 0; i < book_time.length; i++) {
            int start = Integer.parseInt(book_time[i][0].replace(":", ""));
            int end = Integer.parseInt(book_time[i][1].replace(":", ""));
            
            end += 10;
            
            if (end % 100 >= 60) {
                end += 40;
            }
            bookTimes[i][0] = start;
            bookTimes[i][1] = end;
        }
        Arrays.sort(bookTimes, (o1, o2) -> o1[0] - o2[0]);

        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for (int[] time : bookTimes) {
            if (!rooms.isEmpty() && time[0] >= rooms.peek()) {
                rooms.poll();
            }
            rooms.add(time[1]);
        }

        return rooms.size();
    }
}