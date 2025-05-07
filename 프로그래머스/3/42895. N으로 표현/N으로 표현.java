class Solution {
    private static int answer = Integer.MAX_VALUE;
    
    public static int solution(int N, int number) {
        dfs(N, number, 0, 0);

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private static void dfs(int N, int target, int count, int current) {
        if (current == target) {
            answer = Math.min(answer, count);
            return;
        }

        int concatenated = 0;
        for (int i = 1; i <= 8 - count; i++) {
            concatenated = concatenated * 10 + N;

            // 첫 번째 숫자인 경우, 숫자 자체를 사용
            if (count == 0) {
                dfs(N, target, i, concatenated);
            } else {
                dfs(N, target, count + i, current + concatenated); 
                dfs(N, target, count + i, current - concatenated); 
                dfs(N, target, count + i, concatenated - current); 
                dfs(N, target, count + i, current * concatenated);
                if (concatenated != 0) {
                    dfs(N, target, count + i, current / concatenated);
                }

                if (current != 0) {
                    dfs(N, target, count + i, concatenated / current);
                }
            }
        }
    }
}