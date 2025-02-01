import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 각 팀의 카약 상태를 저장하는 배열 (0: 정상, -1: 손상, 1: 여분)
        int[] teams = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int damaged = Integer.parseInt(st.nextToken()) - 1;
            teams[damaged] = -1;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < R; i++) {
            int extra = Integer.parseInt(st.nextToken()) - 1;
            if (teams[extra] == -1) {
                teams[extra] = 0;
            } else {
                teams[extra] = 1;
            }
        }

        for (int i = 0; i < N; i++) {
            if (teams[i] == -1) {
                if (i > 0 && teams[i - 1] == 1) {
                    teams[i] = 0;
                    teams[i - 1] = 0;
                } else if (i < N - 1 && teams[i + 1] == 1) {
                    teams[i] = 0;
                    teams[i + 1] = 0;
                }
            }
        }

        int cantParticipate = 0;
        for (int team : teams) {
            if (team == -1) {
                cantParticipate++;
            }
        }

        System.out.println(cantParticipate);
    }
}