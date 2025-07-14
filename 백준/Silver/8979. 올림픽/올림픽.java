import java.util.*;
import java.io.*;

public class Main {
    static class Country {
        int id;
        int gold;
        int silver;
        int bronze;
        
        Country(int id, int gold, int silver, int bronze) {
            this.id = id;
            this.gold = gold;
            this.silver = silver;
            this.bronze = bronze;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        List<Country> countries = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int bronze = Integer.parseInt(st.nextToken());
            
            countries.add(new Country(id, gold, silver, bronze));
        }
        
        countries.sort((a, b) -> {
            if (a.gold != b.gold) {
                return b.gold - a.gold;
            }
            if (a.silver != b.silver) {
                return b.silver - a.silver;
            }
            return b.bronze - a.bronze;
        });
        
        int rank = 1;
        for (int i = 0; i < N; i++) {
            if (countries.get(i).id == K) {
                System.out.println(rank);
                break;
            }
            
            if (i + 1 < N) {
                Country current = countries.get(i);
                Country next = countries.get(i + 1);
                
                if (current.gold != next.gold || 
                    current.silver != next.silver || 
                    current.bronze != next.bronze) {
                    rank = i + 2;
                }
            }
        }
    }
}