import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] strArr = br.readLine().split("");

        int aTotal = 0;
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("a")) {
                aTotal++;
            }
        }

        int aMax = 0;
        for (int i = 0; i < strArr.length; i++) {
            int aCnt = 0;
            for (int j = 0; j < aTotal; j++) {
                // 원형 배열이므로 strArr 범위를 벗어나면 다시 0번째 부터 시작하도록
                int index = (i + j < strArr.length ? i + j : i + j - strArr.length);
                if (strArr[index].equals("a")) {
                    aCnt++;
                }
                aMax = Math.max(aMax, aCnt);
            }
        }
        System.out.println(aTotal - aMax);
    }
}
