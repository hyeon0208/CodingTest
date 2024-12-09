import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    
    private static int N;
    private static int M;
    private static int L;
    private static int K;

    private static List<StarPosition> starPositions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 구역 가로
        M = Integer.parseInt(st.nextToken()); // 별똥별이 떨어지는 구역 세로
        L = Integer.parseInt(st.nextToken()); // 트램펄린 한 변의 길이
        K = Integer.parseInt(st.nextToken()); // 별똥별의 수

        starPositions = new ArrayList<>(K);
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            starPositions.add(new StarPosition(x, y));
        }

        int matchCount = 0;
        for (StarPosition s1 : starPositions) {
            for (StarPosition s2 : starPositions) {
                // 트램펄린의 왼쪽 상단 모서리 설정
                matchCount = Math.max(matchCount, countStars(s1.x, s2.y, starPositions));
            }
        }

        System.out.println(K - matchCount);
    }

    static int countStars(int x, int y, List<StarPosition> starPositions) {
        int count = 0;
        for (StarPosition s : starPositions) {
            // 현재 별똥별이 트램펄린 범위 내에 있는지 확인
            if (s.x >= x && s.x <= x + L && s.y >= y && s.y <= y + L) {
                count++;
            }
        }
        return count;
    }

    private static class StarPosition {
        int x;
        int y;

        public StarPosition(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
