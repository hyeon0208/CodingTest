import java.util.Arrays;

class Solution {
    
    private static int[] result;
    private static int maxDiff = -1;
    
    public static int[] solution(int n, int[] info) {
        result = new int[11];
        int[] ryan = new int[11];

        // info의 i번째 원소는 과녁의 10 - i 점을 맞힌 화살 개수 (즉, 10점 부터 내림차순)
        // 라이언이 우승할 방법을 구해야함.
        // 없으면 [-1] 반환
        dfs(info, 0, n, ryan);
        if (maxDiff <= 0) {
            return new int[]{-1};
        }

        return result;
    }
    
    private static void dfs(int[] info, int depth, int arrowsLeft, int[] ryan) {
        if (arrowsLeft == 0 || depth == 10) {
            // 남은 화살은 0점에 모두 할당
            ryan[10] = arrowsLeft;

            // 점수 계산
            int ryanScore = 0;
            int apeachScore = 0;

            for (int i = 0; i < 10; i++) {
                if (info[i] == 0 && ryan[i] == 0) {
                    continue;  // 둘 다 화살을 안 쏜 경우
                }

                if (info[i] >= ryan[i]) {
                    apeachScore += 10 - i;  // 어피치가 점수를 가져감
                } else {
                    ryanScore += 10 - i;  // 라이언이 점수를 가져감
                }
            }

            int diff = ryanScore - apeachScore;

            // 라이언이 이기고, 점수 차이가 더 크거나 같은 경우
            if (diff >= maxDiff) {
                // 이전 점수 차이 결과와 같으면 가장 낮은 점수를 더 많이 맞힌 경우를 선택
                if (diff == maxDiff) {
                    for (int i = 10; i >= 0; i--) {
                        if (ryan[i] > result[i]) {
                            result = ryan.clone();
                            break;
                        } else if (ryan[i] < result[i]) {
                            break;
                        }
                    }
                } else {
                    maxDiff = diff;
                    result = ryan.clone();
                }
            }
            return;
        }

        // 현재 점수에 화살을 쏘는 경우
        if (arrowsLeft >= info[depth] + 1) {
            int temp = ryan[depth];
            ryan[depth] = info[depth] + 1;
            dfs(info, depth + 1, arrowsLeft - ryan[depth], ryan);
            ryan[depth] = temp;
        }

        // 현재 점수에 화살을 쏘지 않는 경우
        dfs(info, depth + 1, arrowsLeft, ryan);
    }
}