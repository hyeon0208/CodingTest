import java.util.*;
import java.io.*;
 
public class Main {
    static int N, X, result;
    static int arr[];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        X = Integer.parseInt(br.readLine());
 
        Arrays.sort(arr);
        
        int left=0, right = N-1;
        while(left < right) {
            int sum = arr[left]+arr[right];
            if(sum == X) {
                result++;
                left++;
                right--;
            }else if(sum > X) {
                right--;
            }else {
                left++;
            }
        }
        System.out.println(result);
    }
}
 
