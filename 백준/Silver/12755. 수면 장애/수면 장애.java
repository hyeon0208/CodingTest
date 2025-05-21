import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        int digit = 1; // 현재 숫자의 자릿수 
        long count = 9; // 현재 자릿수의 숫자 개수
        long length = count * digit; // 현재 자릿수 숫자들이 차지하는 총 길이
        
        while (N > length) {
            N -= length;
            digit++;
            count *= 10;
            length = count * digit; 
        }
        
        long startNum = (long) Math.pow(10, digit - 1);
        
        long targetNum = startNum + (N - 1) / digit;
        
        int pos = (N - 1) % digit;
        
        String numStr = String.valueOf(targetNum);
        int result = numStr.charAt(pos) - '0';
        
        System.out.println(result);
    }
}