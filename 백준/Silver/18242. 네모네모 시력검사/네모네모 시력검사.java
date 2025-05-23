import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int startCol = 0, count = 0;
        
        for (int i = 0; i < r; i++) {
            String tmp = br.readLine();
            if (tmp.indexOf('#') == -1) {
                continue;
            }

            startCol = tmp.indexOf('#');
            count = 1;

            // 연속된 '#'의 개수 세기
            for (int j = startCol + 1; j < c; j++) {
                if (tmp.charAt(j) == '.') {
                    break;
                }
                count++;
            }

            // 만약 연속된 '#'의 개수가 실제 범위와 다르면 UP
            if (count != tmp.lastIndexOf('#') - tmp.indexOf('#') + 1) {
                System.out.println("UP");
                return;
            }
            break;
        }

        // (cnt - 2) / 2 만큼 줄을 건너뛰어 중앙 행에 도달
        for (int i = 0; i < (count - 2) / 2; i++) {
            br.readLine();
        }

        String tmp = br.readLine();

        if (tmp.charAt(startCol) == '#' && tmp.charAt(startCol + count - 1) == '#') {
            System.out.println("DOWN");
        } else if (tmp.charAt(startCol) == '#') {
            System.out.println("RIGHT");
        } else {
            System.out.println("LEFT");
        }
    }
}
