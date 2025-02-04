import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Room[] rooms;
    static boolean[] visited;
    
    static class Room {
        char type;
        int amount;
        List<Integer> connected;
        
        Room(char type, int amount) {
            this.type = type;
            this.amount = amount;
            this.connected = new ArrayList<>();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            
            rooms = new Room[N + 1];
            visited = new boolean[N + 1];
            
            // 방 정보 입력
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char type = st.nextToken().charAt(0);
                int amount = Integer.parseInt(st.nextToken());
                rooms[i] = new Room(type, amount);
                
                // 연결된 방 정보 입력
                while (true) {
                    int connected = Integer.parseInt(st.nextToken());
                    if (connected == 0) break;
                    rooms[i].connected.add(connected);
                }
            }
            
            // 탐색 시작
            visited[1] = true;
            System.out.println(dfs(1, 0) ? "Yes" : "No");
        }
    }
    
    static boolean dfs(int current, int money) {
        // 현재 방에서의 돈 계산
        int currentMoney = money;
        
        // 레프리콘인 경우
        if (rooms[current].type == 'L') {
            currentMoney = Math.max(currentMoney, rooms[current].amount);
        }
        // 트롤인 경우
        else if (rooms[current].type == 'T') {
            if (currentMoney < rooms[current].amount) return false;
            currentMoney -= rooms[current].amount;
        }
        
        // 마지막 방에 도달한 경우
        if (current == N) return true;
        
        // 연결된 방들을 탐색
        for (int next : rooms[current].connected) {
            if (!visited[next]) {
                visited[next] = true;
                if (dfs(next, currentMoney)) return true;
                visited[next] = false;
            }
        }
        
        return false;
    }
}