import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int[] skills = new int[2 * n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) {
            skills[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(skills);
        
        int minTeamPower = Integer.MAX_VALUE;
        
        for (int i = 0; i < n; i++) {
            int teamPower = skills[i] + skills[2 * n - 1 - i];
            minTeamPower = Math.min(minTeamPower, teamPower);
        }
        
        System.out.println(minTeamPower);
    }
}