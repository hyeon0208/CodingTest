import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int K = Integer.parseInt(br.readLine());

        int[] directions = new int[6];
        int[] lengths = new int[6];

        int maxWidth = 0, maxHeight = 0;

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            directions[i] = Integer.parseInt(st.nextToken());
            lengths[i] = Integer.parseInt(st.nextToken());

            if (directions[i] == 1 || directions[i] == 2) {
                maxWidth = Math.max(maxWidth, lengths[i]);
            } else {
                maxHeight = Math.max(maxHeight, lengths[i]);
            }
        }

        int smallArea = 0;
        for (int i = 0; i < 6; i++) {
            if ((directions[i] == directions[(i + 2) % 6]) && (directions[(i + 1) % 6] == directions[(i + 3) % 6])) {
                smallArea = lengths[(i + 1) % 6] * lengths[(i + 2) % 6];
                break;
            }
        }

        int totalArea = (maxWidth * maxHeight - smallArea) * K;
        System.out.println(totalArea);
    }
}

