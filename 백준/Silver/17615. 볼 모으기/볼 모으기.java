import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static int N;
    private static String[] balls;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = br.readLine().split("");

        int minMoveCount = Integer.MAX_VALUE;
        minMoveCount = Math.min(minMoveCount, getCountForLeft("R"));
        minMoveCount = Math.min(minMoveCount, getCountForLeft("B"));
        minMoveCount = Math.min(minMoveCount, getCountForRight("R"));
        minMoveCount = Math.min(minMoveCount, getCountForRight("B"));

        System.out.println(minMoveCount);

    }

    private static int getCountForLeft(String type) {
        int result = 0;
        List<Integer> targets = new ArrayList<>(N);
        int firstOtherIndex = Integer.MAX_VALUE;
        boolean findOtherFirst = false;
        for (int i = 0; i < N; i++) {
            if (balls[i].equals(type)) {
                targets.add(i);
            } else if (!findOtherFirst) {
                findOtherFirst = true;
                firstOtherIndex = i;
            }
        }

        for (int i = 0; i < targets.size(); i++) {
            if (firstOtherIndex < targets.get(i)) {
                result++;
            }
        }
        return result;
    }

    private static int getCountForRight(String type) {
        int result = 0;
        List<Integer> targets = new ArrayList<>(N);
        int firstOtherIndex = Integer.MIN_VALUE;
        boolean findOtherFirst = false;
        for (int i = N - 1; i >= 0; i--) {
            if (balls[i].equals(type)) {
                targets.add(i);
            } else if (!findOtherFirst) {
                findOtherFirst = true;
                firstOtherIndex = i;
            }
        }

        for (int i = 0; i < targets.size(); i++) {
            if (firstOtherIndex > targets.get(i)) {
                result++;
            }
        }
        return result;
    }
}
