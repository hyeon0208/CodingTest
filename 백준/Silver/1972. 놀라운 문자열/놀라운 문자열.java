import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String input;
        while(!(input = br.readLine()).equals("*")) {
            int len = input.length();
            boolean isSurprise = true;
            for(int i = 1; i < len; i++) {
                HashSet<String> set = new HashSet<>();
                for(int j = 0; j < len - i; j++) {
                    String str = input.charAt(j) + "" + input.charAt(j + i);
                    if(!set.add(str)) {
                        isSurprise = false;
                        break;
                    }
                }
            }
            sb.append(input).append(isSurprise ? " is surprising." : " is NOT surprising.").append("\n");
        }
        System.out.println(sb);
    }
}
