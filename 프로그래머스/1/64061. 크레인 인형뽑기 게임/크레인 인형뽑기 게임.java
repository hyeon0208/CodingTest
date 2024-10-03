import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        Stack<Integer> bottle = new Stack<>();

        for (int m = 0; m < moves.length; m++) {
            int index = moves[m] - 1;
            for (int i = 0; i < board.length; i++) {
                if (board[i][index] != 0) {
                    if (!bottle.isEmpty() && bottle.peek() == board[i][index]) {
                        bottle.pop();
                        answer += 2;
                    } else {
                        bottle.push(board[i][index]);
                    }
                    board[i][index] = 0;
                    break;
                }

            }
        }
        
        return answer;
    }
}