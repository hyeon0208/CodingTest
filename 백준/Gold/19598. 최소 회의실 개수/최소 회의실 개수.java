import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        // 회의 정보 저장 (시작 시간, 종료 시간)
        int[][] meetings = new int[N][2];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken()); // 시작
            meetings[i][1] = Integer.parseInt(st.nextToken()); // 종료
        }
        
        // 시작 시간 기준 오름차순 정렬
        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);
        
        // 우선순위 큐 (최소 힙): 회의실들의 종료 시간 저장
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int i = 0; i < N; i++) {
            // 가장 빨리 끝나는 회의실이 현재 회의 시작 전에 끝나면
            if (!pq.isEmpty() && pq.peek() <= meetings[i][0]) {
                pq.poll(); // 해당 회의실 재사용
            }
            // 현재 회의의 종료 시간 추가
            pq.offer(meetings[i][1]);
        }
        
        // 우선순위 큐의 크기 = 필요한 최소 회의실 개수
        System.out.println(pq.size());
    }
}