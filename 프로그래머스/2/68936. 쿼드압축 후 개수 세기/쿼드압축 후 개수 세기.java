class Solution {
    static int[] answer;
                
    public int[] solution(int[][] arr) {
        answer = new int[2];
        quad(arr.length, 0, 0, arr);

        return answer;
    }

    public static void quad(int size, int x, int y, int[][] arr) {
        if (size == 1) {
            answer[arr[x][y]]++;
            return;
        }

        if (isSameBlock(size, x, y, arr)) {
            return;
        }

        quad(size / 2, x, y, arr); // 1 사분면
        quad(size / 2, x, y + size / 2, arr); // 2 사분면
        quad(size / 2, x + size / 2, y, arr); // 3 사분면
        quad(size / 2, x + size / 2, y + size / 2, arr); // 4 사분면
    }

    public static boolean isSameBlock(int size, int x, int y, int[][] arr) {
        int currentValue = arr[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (currentValue != arr[i][j]) {
                    return false;
                }
            }
        }

        answer[currentValue]++;
        return true;
    }
}