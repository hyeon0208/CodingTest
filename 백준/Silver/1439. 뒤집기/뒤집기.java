import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer one = new StringTokenizer(s, "0");
        StringTokenizer zero = new StringTokenizer(s, "1");
        System.out.println(Math.min(one.countTokens(), zero.countTokens()));
    }
}