import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        List<Integer> sensors = new ArrayList<>(N);
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            sensors.add(Integer.parseInt(st.nextToken()));
        }
        sensors.sort(Comparator.comparingInt(Integer::intValue));

        Integer[] distances = new Integer[N - 1];
        for (int i = 0; i < N - 1; i++) {
            distances[i] = sensors.get(i + 1) - sensors.get(i);
        }
        Arrays.sort(distances, Collections.reverseOrder());

        int result = 0;
        // K - 1은 K 개의 그룹으로 나누기 위한 가장 큰 거리를 가진 구분선 개수가 되므로 건너 뛰고 거리 합 계산
        for (int i = K - 1; i < distances.length; i++) {
            result += distances[i];
        }

        System.out.println(result);
    }
}
