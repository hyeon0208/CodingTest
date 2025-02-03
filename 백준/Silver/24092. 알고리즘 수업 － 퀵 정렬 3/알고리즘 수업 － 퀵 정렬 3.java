import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static boolean found = false;
    private static int[] arr;
    private static int[] target;
    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        target = new int[N];
        for (int i = 0; i < N; i++) {
            target[i] = Integer.parseInt(st.nextToken());
        }

        if (Arrays.equals(arr, target)) {
            System.out.println(1);
            return;
        }

        quickSort(arr, 0, N - 1);

        if (found) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    private static void quickSort(int[] arr, int start, int end) {
        if (found) {
            return;
        }

        if (start < end) { // 정렬할 원소가 2개 이상일 때만 실행
            int pivotIndex = partition(arr, start, end);
            if (pivotIndex < 0) {
                return;
            }
            quickSort(arr, start, pivotIndex - 1); // pivotIndex 기준 왼쪽 정렬
            quickSort(arr, pivotIndex + 1, end); // pivotIndex 기준 오른쪽 정렬
        }
    }

    private static int partition(int[] arr, int start, int end) {
        int pivot = arr[end];
        int left = start;  // 피벗보다 작은 값들의 영역

        for (int j = start; j < end; j++) {
            if (arr[j] <= pivot) {
                if (left != j) {
                    swap(arr, left, j);
                    if (Arrays.equals(arr, target)) {
                        found = true;
                        return -1;
                    }
                }
                left++;
            }
        }

        /*
        // 피벗을 자기 위치로 이동 (위 과정이 끝나면 left+1 위치는 "피벗이 들어가야 할 위치"가 됨)
        left + 1: 피벗이 있어야 할 위치
        end: 현재 피벗의 위치
        두 값이 다르면: 피벗을 이동해야 함
        두 값이 같으면: 피벗이 이미 올바른 위치에 있으므로 이동할 필요 없음
         */
        if (left != end) {
            swap(arr, left, end);
            if (Arrays.equals(arr, target)) {
                found = true;
                return -1;
            }
        }

        return left;  // 피벗의 최종 위치 반환
    }

    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
