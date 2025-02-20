import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int target;            // 구매하고자 하는 피자크기
    private static int m, n;             // A, B 피자의 조각 개수

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        target = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        // A 피자 누적합 계산
        int[] sumA = new int[2 * m + 1];
        for (int i = 1; i <= m; i++) {
            sumA[i] = sumA[i - 1] + Integer.parseInt(br.readLine());
            sumA[i + m] = sumA[i];
        }
        for (int i = m + 1; i <= 2 * m; i++) {
            sumA[i] += sumA[m];
        }

        // B 피자 누적합 계산
        int[] sumB = new int[2 * n + 1];
        for (int i = 1; i <= n; i++) {
            sumB[i] = sumB[i - 1] + Integer.parseInt(br.readLine());
            sumB[i + n] = sumB[i];
        }
        for (int i = n + 1; i <= 2 * n; i++) {
            sumB[i] += sumB[n];
        }

        // 각 피자에서 나올 수 있는 부분합의 개수 계산
        int[] cntA = new int[target + 1];
        int[] cntB = new int[target + 1];

        countPossibleSums(sumA, cntA, m);
        countPossibleSums(sumB, cntB, n);

        // 정답 계산
        long answer = 0;

        // 1. A피자만 사용하는 경우
        answer += cntA[target];

        // 2. B피자만 사용하는 경우
        answer += cntB[target];

        // 3. A, B 피자를 조합하는 경우
        for (int i = 1; i < target; i++) {
            answer += (long) cntA[i] * cntB[target - i];
        }

        System.out.println(answer);
    }

    // 한 피자에서 나올 수 있는 모든 부분합의 개수를 계산하는 함수
    private static void countPossibleSums(int[] sum, int[] cnt, int size) {
        // i: 연속해서 선택할 조각의 개수
        for (int i = 1; i < size; i++) {
            // s: 시작 위치
            for (int s = 1; s <= size; s++) {
                // sum[s + i - 1] - sum[s - 1]: s부터 i개의 연속된 조각의 합
                int value = sum[s + i - 1] - sum[s - 1];
                if (value > target) {
                    continue;
                }
                cnt[value]++;
            }
        }

        // 피자 전체를 선택하는 경우
        if (sum[size] <= target) {
            cnt[sum[size]]++;
        }
    }
}