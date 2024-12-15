import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            String paper = br.readLine();
            sb.append(isPossible(paper) ? "YES" : "NO").append("\n");
        }

        System.out.print(sb);
    }

    private static boolean isPossible(String paper) {
        return check(paper.toCharArray(), 0, paper.length() - 1);
    }

    private static boolean check(char[] paper, int start, int end) {
        // 길이가 1이하면 더 이상 검사할 필요 없음
        if (start >= end) {
            return true;
        }

        // 가운데를 기준으로 양쪽 검사
        int mid = (start + end) / 2;

        // 양쪽 대칭되는 위치의 값이 반대인지 확인
        for (int i = start; i < mid; i++) {
            // 대칭되는 위치
            if (paper[i] == paper[end - (i - start)]) {
                return false;
            }
        }

        /** 왼쪽 부분 재귀적으로 검사
         * 3번 접은 패턴이 가능하려면
         * -> 2번 접은 패턴도 가능해야 하고
         * -> 1번 접은 패턴도 가능해야 한다.
         */
        return check(paper, start, mid - 1);
    }
}
