import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int MAN = 1;
    private static final int GIRL = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int switchCount = Integer.parseInt(br.readLine());
        int[] switches = new int[switchCount + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= switchCount; i++) {
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int studentCount = Integer.parseInt(br.readLine());
        for (int i = 0; i < studentCount; i++) {
            st = new StringTokenizer(br.readLine());
            int sexNum = Integer.parseInt(st.nextToken());
            int switchNumberIndex = Integer.parseInt(st.nextToken());

            if (sexNum == MAN) {
                for (int j = switchNumberIndex; j <= switchCount; j += switchNumberIndex) {
                    switches[j] = reverse(switches[j]);
                }
            }

            if (sexNum == GIRL) {
                switches[switchNumberIndex] = reverse(switches[switchNumberIndex]);

                int leftSwitchNumberIndex = switchNumberIndex - 1;
                int rightSwitchNumberIndex = switchNumberIndex + 1;
                while (leftSwitchNumberIndex >= 1 && rightSwitchNumberIndex <= switchCount && 
                       switches[leftSwitchNumberIndex] == switches[rightSwitchNumberIndex]) {
                    switches[leftSwitchNumberIndex] = reverse(switches[leftSwitchNumberIndex]);
                    switches[rightSwitchNumberIndex] = reverse(switches[rightSwitchNumberIndex]);

                    leftSwitchNumberIndex--;
                    rightSwitchNumberIndex++;
                }
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= switchCount; i++) {
            stringBuilder.append(switches[i]).append(" ");
            if (i % 20 == 0) {
                stringBuilder.append("\n");
            }
        }

        System.out.println(stringBuilder);
    }

    public static int reverse(int cur) {
        return cur == 1 ? 0 : 1;
    }
}
