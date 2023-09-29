import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] Dgraph = new int[1001][1001];
    static int[][] Bgraph = new int[1001][1001];
    static boolean[] Dvisit = new boolean[10001];
    static boolean[] Bvisit = new boolean[10001];
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        //간선을 두개의 그래프 배열에 똑같이 저장해 준다.
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            Dgraph[u][v] = Dgraph[v][u] = 1;
            Bgraph[u][v] = Bgraph[v][u] = 1;
        }
        DFS(V);
        System.out.println();
        BFS(V);
    }

    public static void DFS(int start) {
        Dvisit[start] = true;
        System.out.print(start + " ");

        for(int i = 1; i <= N; i++) {
            if(!Dvisit[i] && Dgraph[start][i] == 1) {
                DFS(i);
            }
        }
    }

    public static void BFS(int start) {
        Queue<Integer> que = new LinkedList<Integer>();
        Bvisit[start] = true;
        que.offer(start);

        while(!que.isEmpty()) {
            int cur = que.poll();
            System.out.print(cur + " ");

            for(int i = 1; i <= N; i++) {
                if(!Bvisit[i] && Bgraph[cur][i] == 1) {
                    Bvisit[i] = true;
                    que.offer(i);
                }
            }
        }
    }
}