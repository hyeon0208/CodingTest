import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int k = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        int count = 0;

        LinkedHashSet<String> set = new LinkedHashSet<>();
        for(int i=0; i<l; i++) {
            String number = br.readLine();
            if(set.contains(number)) {
                set.remove(number);
            }
            set.add(number);
        }

        for (String s : set) {
            count++;
            System.out.println(s);
            if (count == k) {
                break;
            }
        }
    }
}