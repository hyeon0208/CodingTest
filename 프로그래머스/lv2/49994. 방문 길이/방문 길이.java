import java.util.*;

class Solution {
    public int solution(String dirs) {
        int answer = 0;

        HashSet<String> visited = new HashSet<>();

        int currentX = 0;
        int currentY = 0;

        for (int i = 0; i < dirs.length(); i++) {
            int nextX = currentX;
            int nextY = currentY;
            String moving = "";

            if (dirs.charAt(i) == 'U') {
                nextY++;
                moving += currentX + "" + currentY + "->" + nextX + "" + nextY + "";
            } else if (dirs.charAt(i) == 'D') {
                nextY--;
                moving += nextX + "" + nextY + "->" + currentX + "" + currentY + "";
            } else if (dirs.charAt(i) == 'R') {
                nextX++;
                moving += currentX + "" + currentY + "->" + nextX + "" + nextY + "";
            } else {
                nextX--;
                moving += nextX + "" + nextY + "->" + currentX + "" + currentY + "";
            }

            // 좌표평면의 경계를 넘어가면 추가하지 않고 다음 명령으로 넘어간다.
            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) {
                continue;
            }
            visited.add(moving);

            currentX = nextX;
            currentY = nextY;
        }
        answer = visited.size();

        return answer;
    }
}