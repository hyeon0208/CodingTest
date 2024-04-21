import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final char[] SOUND = {'q', 'u', 'a', 'c', 'k'};
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] inputArr = br.readLine().toCharArray();
        boolean[] visited = new boolean[inputArr.length];

        if (inputArr.length % 5 != 0 || inputArr[0] != 'q') {
            System.out.println(-1);
            return;
        }

        int soundIndex = 0;
        for (int i = 0; i < inputArr.length; i++) {
            List<Character> list = new ArrayList<>();
            for (int j = i; j < inputArr.length; j++) {
                if (!visited[j] && inputArr[j] == SOUND[soundIndex]) {
                    soundIndex++;
                    visited[j] = true;
                    list.add(inputArr[j]);
                    if (soundIndex == 5) {
                        soundIndex = 0;
                    }
                }
            }
            if (!list.isEmpty()) {
                if (list.get(list.size() - 1) != 'k') {
                    System.out.println(-1);
                    return;
                }
                result++;
            }
        }
        System.out.println(result);
    }
}


