import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();
        int[] alphabet = new int[26];
        for (int i = 0; i < name.length(); i++) {
            int idx = name.charAt(i) - 'A';
            alphabet[idx]++;
        }

        int oddNumberCount = 0;
        for (int i = 0; i < alphabet.length; i++) {
            if (alphabet[i] % 2 != 0) {
                oddNumberCount++;
            }
        }

        String answer = "";
        StringBuilder sb = new StringBuilder();
        if (oddNumberCount > 1) {
            answer += "I'm Sorry Hansoo";
        } else {
            for (int i = 0; i < alphabet.length; i++) {
                for (int j = 0; j < alphabet[i] / 2; j++) {
                    sb.append((char) (i + 'A'));
                }
            }
            answer += sb.toString();
            String end = sb.reverse().toString();

            sb = new StringBuilder();
            for (int i = 0; i < alphabet.length; i++) {
                if (alphabet[i] % 2 == 1) {
                    sb.append((char) (i + 'A'));
                }
            }
            answer += sb.toString() + end;
        }
        System.out.println(answer);
    }
}