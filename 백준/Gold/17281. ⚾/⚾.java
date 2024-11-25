import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int[][] results;
    private static boolean[] selected;
    private static int[] order;
    private static int maxScore = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        results = new int[N][9];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                results[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        selected = new boolean[9];
        order = new int[9];
        order[3] = 0;  // 4번 타자는 1번 선수로 고정
        selected[0] = true;

        makeLineup(0);
        System.out.println(maxScore);
    }

    private static void makeLineup(int index) {
        if (index == 9) {
            int score = playGame();
            maxScore = Math.max(maxScore, score);
            return;
        }

        // 4번 타자는 이미 1번 선수로 고정되어 있으므로 건너뛰기
        if (index == 3) {
            makeLineup(index + 1);
            return;
        }

        // 1~9번 선수들을 현재 타순에 배치
        for (int i = 1; i < 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                order[index] = i;
                makeLineup(index + 1);
                selected[i] = false;
            }
        }
    }

    private static int playGame() {
        int score = 0;
        int currentOrder = 0;

        for (int inning = 0; inning < N; inning++) {
            int outCount = 0;
            boolean[] bases = new boolean[3];

            while (outCount < 3) {
                int hit = results[inning][order[currentOrder]];

                if (hit == 0) {
                    outCount++;
                } else {
                    score += runBases(bases, hit);
                }

                currentOrder = (currentOrder + 1) % 9;
            }
        }

        return score;
    }

    private static int runBases(boolean[] bases, int hit) {
        int score = 0;

        if (hit == 4) {
            score = 1;  // 타자 본인
            for (int i = 0; i < 3; i++) {
                if (bases[i]) {
                    score++;
                }
                bases[i] = false;
            }
            return score;
        }

        for (int i = 2; i >= 0; i--) {
            if (bases[i]) {
                if (i + hit >= 3) {
                    score++;
                    bases[i] = false;
                } else {
                    bases[i + hit] = true;
                    bases[i] = false;
                }
            }
        }

        bases[hit - 1] = true;  // 타자를 해당 루에 위치 (base[0]은 1루를 의미)
        return score;
    }
}
