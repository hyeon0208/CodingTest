import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] children = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        // LIS를 저장할 배열
        int[] lis = new int[N];
        lis[0] = children[0];  // 첫 번째 원소로 초기화
        int lisLength = 1;     // 현재 LIS의 길이

        // 각 원소에 대해
        for (int i = 1; i < N; i++) {
            int current = children[i];

            // 현재 값이 LIS의 마지막 값보다 크면 추가
            if (current > lis[lisLength - 1]) {
                lis[lisLength++] = current;
            }
            // 그렇지 않으면 들어갈 위치를 이분 탐색으로 찾아서 교체
            else {
                int position = binarySearch(lis, 0, lisLength - 1, current);
                lis[position] = current;
            }
        }

        // LIS에 속하지 않은 아이들의 수가 최소 이동 횟수
        System.out.println(N - lisLength);
    }

    // 이분 탐색으로 current가 들어갈 위치를 찾는 메서드
    private static int binarySearch(int[] lis, int left, int right, int current) {
        while (left < right) {
            int mid = (left + right) / 2;
            /*
            LIS 배열: [2, 5, 7]
            current: 4를 삽입하려는 경우, mid = 1, lis[1] = 5, 5 >= 4 이므로 right = 1 (인덱스 1이 current가 들어갈 후보 위치)
             */
            if (lis[mid] >= current) { //
                right = mid;
            } else { // current가 mid 보다 크면 mid 다음에 넣으면 됨
                left = mid + 1;
            }
        }
        return left;
    }
}
