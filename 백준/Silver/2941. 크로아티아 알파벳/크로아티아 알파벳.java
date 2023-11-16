import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        br.close();

        String[] croatiaAlphabet ={"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};
        int cnt = 0;

        for(String val : croatiaAlphabet) {
            while(str.contains(val) ) {
                str = str.replace(val, "A");
            }
        }

        cnt += str.length();

        System.out.print(cnt);
    }
}

