import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(bfs());
    }

    static public String bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        q.add(new Node(0, 0, board[0][0]));
        while (!q.isEmpty()) {
            Node cur = q.poll();
            int x = cur.x;
            int y = cur.y;
            int count = cur.count;
            if (board[x][y] == -1) {
                return "HaruHaru";
            }
            if (!visited[x][y]) {
                visited[x][y] = true;
                if (x + count < N && !visited[x + count][y]) {
                    q.add(new Node(x + count, y, board[x + count][y]));
                }
                if (y + count < N && !visited[x][y + count]) {
                    q.add(new Node(x, y + count, board[x][y + count]));
                }
            }
        }
        return "Hing";
    }
}

class Node {
    int x;
    int y;
    int count;

    public Node(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}