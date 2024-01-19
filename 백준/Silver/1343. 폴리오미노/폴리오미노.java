import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        int polyCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == 'X') {
                polyCount++;
            } else {
                if (polyCount % 2 == 1) {
                    sb = new StringBuilder();
                    sb.append(-1);
                    break;
                }

                while (polyCount != 0) {
                    if (polyCount >= 4) {
                        sb.append("AAAA");
                        polyCount -= 4;
                    } else {
                        sb.append("BB");
                        polyCount -= 2;
                    }
                }

                sb.append(".");

            }
        }
        if (polyCount > 0) {
            if (polyCount % 2 == 1) {
                sb = new StringBuilder();
                sb.append(-1);
            } else {
                while (polyCount != 0) {

                    if (polyCount >= 4) {
                        sb.append("AAAA");
                        polyCount -= 4;
                    } else {
                        sb.append("BB");
                        polyCount -= 2;
                    }
                }
            }


        }
        sb.append("\n");
        System.out.println(sb);
    }
}