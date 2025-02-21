import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] buildings = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            buildings[i] = Long.parseLong(st.nextToken());
        }

        int maxVisible = 0;
        for (int i = 0; i < N; i++) {
            int visible = 0;

            // 현재 건물(i)에서 왼쪽에 있는 모든 건물(j)을 확인
            for (int j = 0; j < i; j++) {
                boolean canSee = true;

                // k는 j와 i 사이의 건물
                for (int k = j + 1; k < i; k++) {
                    double slope1 = (double)(buildings[k] - buildings[j]) / (k - j);
                    double slope2 = (double)(buildings[i] - buildings[j]) / (i - j);

                    if (slope1 >= slope2) {
                        canSee = false;
                        break;
                    }
                }

                if (canSee) {
                    visible++;
                }
            }

            // 오른쪽 방향 확인
            for (int j = i + 1; j < N; j++) {
                boolean canSee = true;

                // i와 j 사이의 건물들 확인
                for (int k = i + 1; k < j; k++) {
                    double slope1 = (double)(buildings[k] - buildings[i]) / (k - i);
                    double slope2 = (double)(buildings[j] - buildings[i]) / (j - i);

                    if (slope1 >= slope2) {
                        canSee = false;
                        break;
                    }
                }

                if (canSee) {
                    visible++;
                }
            }

            maxVisible = Math.max(maxVisible, visible);
        }

        System.out.println(maxVisible);
    }
}
