import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String dna = br.readLine();

        st = new StringTokenizer(br.readLine());
        int[] minCount = new int[4]; // A, C, G, T의 최소 개수
        for (int i = 0; i < 4; i++) {
            minCount[i] = Integer.parseInt(st.nextToken());
        }

        int[] currentCount = new int[4];
        int result = 0;

        // 초기 윈도우 설정
        for (int i = 0; i < P; i++) {
            addChar(currentCount, dna.charAt(i));
        }

        // 첫 윈도우 체크
        if (isValid(currentCount, minCount)) {
            result++;
        }

        // 슬라이딩 윈도우
        for (int i = P; i < S; i++) {
            removeChar(currentCount, dna.charAt(i - P)); // 이전 문자 제거
            addChar(currentCount, dna.charAt(i));        // 새 문자 추가

            if (isValid(currentCount, minCount)) {
                result++;
            }
        }

        System.out.println(result);
    }

    private static void addChar(int[] count, char c) {
        switch (c) {
            case 'A': count[0]++; break;
            case 'C': count[1]++; break;
            case 'G': count[2]++; break;
            case 'T': count[3]++; break;
        }
    }

    private static void removeChar(int[] count, char c) {
        switch (c) {
            case 'A': count[0]--; break;
            case 'C': count[1]--; break;
            case 'G': count[2]--; break;
            case 'T': count[3]--; break;
        }
    }

    private static boolean isValid(int[] current, int[] min) {
        for (int i = 0; i < 4; i++) {
            if (current[i] < min[i]) return false;
        }
        return true;
    }
}
