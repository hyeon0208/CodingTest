import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int count;
    private static boolean found;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        quickSort(arr, 1, N, K);

        if (found) {
            printResult(arr);
        } else {
            System.out.println(-1);
        }
    }

    private static void quickSort(int[] arr, int start, int end, int K) {
        if (found) {
            return;
        }

        if (start < end) { // 정렬할 원소가 2개 이상일 때만 실행
            int pivotIndex = partition(arr, start, end, K);
            if (pivotIndex < 0) {
                return;
            }
            quickSort(arr, start, pivotIndex - 1, K); // pivotIndex 기준 왼쪽 정렬
            quickSort(arr, pivotIndex + 1, end, K); // pivotIndex 기준 오른쪽 정렬
        }
    }

    private static int partition(int[] arr, int start, int end, int K) {
        int pivot = arr[end];
        int left = start - 1;  // 피벗보다 작은 값들의 영역

        // 피벗보다 작거나 같은 값들을 왼쪽으로 모으기
        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                left++;
                swap(arr, left, j);
                if (count == K) {
                    found = true;
                    return -1;
                }
            }
        }

        /*
        // 피벗을 자기 위치로 이동 (위 과정이 끝나면 left+1 위치는 "피벗이 들어가야 할 위치"가 됨)
        left + 1: 피벗이 있어야 할 위치
        end: 현재 피벗의 위치
        두 값이 다르면: 피벗을 이동해야 함
        두 값이 같으면: 피벗이 이미 올바른 위치에 있으므로 이동할 필요 없음
         */
        if (left + 1 != end) {
            swap(arr, left + 1, end);
            if (count == K) {
                found = true;
                return -1;
            }
        }

        return left + 1;  // 피벗의 최종 위치 반환
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        count++;
    }

    private static void printResult(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            sb.append(arr[i]).append(" ");
        }
        System.out.println(sb);
    }
}
