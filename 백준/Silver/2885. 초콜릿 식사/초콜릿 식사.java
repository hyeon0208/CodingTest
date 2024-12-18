import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        int size = 1;
        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        while (size < K) { // 가장 작은 초콜릿 크기 구하기
            size *= 2;
        }
        sb.append(size).append(" ");

        while (K > 0) {
            if (K >= size) { // 사용할 수 있는 조각들을 K에서 차감
                K -= size;
            } else { // 만들어야하는 초콜릿 크기 K 보다 size가 크다면 절반씩 쪼개기
                size /= 2;
                cnt++;
            }
        }
        sb.append(cnt);

        System.out.println(sb);
    }
}
