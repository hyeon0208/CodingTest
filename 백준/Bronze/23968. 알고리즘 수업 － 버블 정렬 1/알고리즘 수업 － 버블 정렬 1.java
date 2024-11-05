import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int count = 0;  // 교환 횟수를 카운트
        boolean found = false;  // K번째 교환을 찾았는지 여부

        // 버블 정렬 구현
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < N - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {  // 교환이 필요한 경우
                    // 두 원소 교환
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    count++;  // 교환 횟수 증가

                    if (count == K) {  // K번째 교환인 경우
                        System.out.println(arr[j] + " " + arr[j + 1]);
                        found = true;
                        break;
                    }
                }
            }
        }

        // K번째 교환이 발생하지 않은 경우
        if (!found) {
            System.out.println(-1);
        }
    }
}