import java.util.*;

class Solution {
    public int solution(String[] board) {
        int Ocount = 0;
        int Xcount = 0;
        
        for (int i = 0; i < board.length; i++) {
            Ocount += board[i].length() - board[i].replace(String.valueOf('O'), "").length();
            Xcount += board[i].length() - board[i].replace(String.valueOf('X'), "").length();
        }
        
        if (Ocount > Xcount + 1) {
            return 0;
        }
        
        if (Xcount > Ocount) {
            return 0;
        }
        
        if (tikTakTo(board, 'O')) {
            if (Ocount == Xcount) {
                return 0;
            }
        }
        
        if (tikTakTo(board, 'X')) {
            if (Ocount == Xcount + 1) {
                return 0;
            }
        }
        
        return 1;
    }
    
    private boolean tikTakTo(String[] board, char ch) {
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == ch
                    && board[i].charAt(1) == ch
                    && board[i].charAt(2) == ch) {
                return true;
            }
        }

        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == ch
                    && board[1].charAt(i) == ch
                    && board[2].charAt(i) == ch) {
                return true;
            }
        }

        if (board[0].charAt(0) == ch
                && board[1].charAt(1) == ch
                && board[2].charAt(2) == ch) {
            return true;
        }
        
        if (board[0].charAt(2) == ch
                && board[1].charAt(1) == ch
                && board[2].charAt(0) == ch) {
            return true;
        }
        
        return false;
    }
}