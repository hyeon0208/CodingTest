import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        LinkedList<Belt> belts = new LinkedList<>();
        for (int i = 0; i < 2 * N; i++) {
            belts.add(new Belt(Integer.parseInt(st.nextToken())));
        }
        Conveyor conveyor = new Conveyor(belts);

        while (K > 0) {
            conveyor.turn();
            conveyor.moveRobot();
            result++;
        }

        System.out.println(result);
    }

    static class Conveyor {
        LinkedList<Belt> belts;

        public Conveyor(LinkedList<Belt> belts) {
            this.belts = belts;
        }

        public void turn() {
            belts.add(0, belts.removeLast());
            if (belts.get(N - 1).robot) {
                belts.get(N - 1).robot = false;
            }
        }

        public void moveRobot() {
            for (int i = N - 1; i > 0; i--) {
                // 벨트 위에 로봇 없으면 패스
                if (!belts.get(i).robot) {
                    continue;
                }

                // 다음 벨트에 로봇 있거나 내구도 0이면 이동 불가
                if (belts.get(i + 1).robot || belts.get(i + 1).durability <= 0) {
                    continue;
                }

                // 이동조건이 성립되면 로봇 다음 칸으로 이동, 내구도 깎기
                belts.get(i).robot = false;
                belts.get(i + 1).putRobot();
                if (belts.get(i + 1).durability <= 0) {
                    K--;
                }

                // 내리는 위치로 로봇 이동했다면 로봇 내리기
                if (i + 1 == N - 1) {
                    belts.get(i + 1).robot = false;
                }
            }

            // 올리는 위치에 내구도 1 이상이면 로봇 올림
            if (belts.get(0).durability > 0) {
                belts.get(0).putRobot();
                if (belts.get(0).durability <= 0) {
                    K--;
                }
            }
        }
    }

    static class Belt {
        int durability;
        boolean robot;

        public Belt(int durability) {
            this.durability = durability;
            robot = false;
        }

        public void putRobot() {
            robot = true;
            durability--;
        }
    }
}
