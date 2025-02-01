import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] mileages = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int appliedPeopleCount = Integer.parseInt(st.nextToken()); // 과목에 신청한 사람 수
            int limitCount = Integer.parseInt(st.nextToken()); // 과목의 수강 인원

            st = new StringTokenizer(br.readLine());
            if (appliedPeopleCount < limitCount) {
                mileages[i] = 1;
            } else {
                int[] applied = new int[appliedPeopleCount];
                for (int j = 0; j < appliedPeopleCount; j++) {
                    applied[j] = Integer.parseInt(st.nextToken());
                }
                Arrays.sort(applied);
                mileages[i] = applied[appliedPeopleCount - limitCount];
            }
        }

        Arrays.sort(mileages);
        int result = 0;
        for (int mile : mileages) {
            if (M >= mile) {  // 남은 마일리지가 필요한 마일리지보다 크거나 같은 경우만
                M -= mile;
                result++;
            } else {
                break;
            }
        }
        System.out.println(result);
    }
}
