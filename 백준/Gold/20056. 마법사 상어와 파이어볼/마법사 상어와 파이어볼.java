import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, K;
    private static final int[] DX = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] DY = {0, 1, 1, 1, 0, -1, -1, -1};
    private static List<FireBall> fireBalls = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireBalls.add(new FireBall(r, c, m, s, d));
        }

        while (K-- > 0) {
            moveFireBalls();
            combineFireBalls();
        }

        int result = 0;
        for (FireBall fb : fireBalls) {
            result += fb.m;
        }

        System.out.println(result);
    }

    private static void moveFireBalls() {
        for (FireBall fb : fireBalls) {
            // 이동 거리 계산 (speed * 방향)
            int nx = (fb.r + DX[fb.d] * (fb.s % N) + N) % N;
            int ny = (fb.c + DY[fb.d] * (fb.s % N) + N) % N;
            fb.r = nx;
            fb.c = ny;
        }
    }

    // 같은 칸의 파이어볼 처리
    private static void combineFireBalls() {
        Map<String, ArrayList<FireBall>> map = new HashMap<>();

        // 같은 위치의 파이어볼들을 그룹화
        for(FireBall fb : fireBalls) {
            String key = fb.r + "," + fb.c;
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(fb);
        }

        ArrayList<FireBall> newFireBalls = new ArrayList<>();

        // 각 위치별로 파이어볼 처리
        for(ArrayList<FireBall> list : map.values()) {
            if(list.size() == 1) {
                newFireBalls.add(list.get(0));
                continue;
            }

            // 2개 이상인 경우 처리
            int totalM = 0, totalS = 0;
            boolean allEven = true;
            boolean allOdd = true;

            for(FireBall fb : list) {
                totalM += fb.m;
                totalS += fb.s;
                if(fb.d % 2 == 0) allOdd = false;
                else allEven = false;
            }

            int newM = totalM / 5;
            if(newM == 0) continue;

            int newS = totalS / list.size();
            int[] newDirections = (allEven || allOdd) ? new int[]{0,2,4,6} : new int[]{1,3,5,7};

            // 4개의 새로운 파이어볼 생성
            for(int nd : newDirections) {
                newFireBalls.add(new FireBall(list.get(0).r, list.get(0).c, newM, newS, nd));
            }
        }

        fireBalls = newFireBalls;
    }

    static class FireBall {
        int r, c, m, s, d;

        FireBall(int r, int c, int m, int s, int d) {
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
