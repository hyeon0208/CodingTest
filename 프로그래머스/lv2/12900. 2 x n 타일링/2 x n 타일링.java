class Solution {
    public int solution(int n) {
        int[] arr = new int[n];
        arr[0] = 1; // 세로
        arr[1] = 2; // 가로

        // arr[2] 부터 시작
        for (int i = 2; i < arr.length; i++) {
            arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
        }

        return arr[n - 1];
    }
}