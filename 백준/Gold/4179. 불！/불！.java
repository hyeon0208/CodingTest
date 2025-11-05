import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	private static final int[] DX = {1, -1, 0, 0};
	private static final int[] DY = {0, 0, 1, -1};
	private static final char WALL = '#';
	private static final char START = 'J';
	private static final char FIRE = 'F';
	private static final int INF = Integer.MAX_VALUE;

	private static int N;
	private static int M;
	private static char[][] maps;
	private static int[][] fireTime;  // 각 칸에 불이 도달하는 시간
	private static int[][] jihunTime;  // 지훈이가 각 칸에 도달하는 시간

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maps = new char[N][M];
		fireTime = new int[N][M];
		jihunTime = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				fireTime[i][j] = INF;
				jihunTime[i][j] = INF;
			}
		}

		int startX = 0;
		int startY = 0;
		Queue<Position> fireQueue = new LinkedList<>();

		for (int x = 0; x < N; x++) {
			String str = br.readLine();
			for (int y = 0; y < M; y++) {
				maps[x][y] = str.charAt(y);
				if (maps[x][y] == START) {
					startX = x;
					startY = y;
				} else if (maps[x][y] == FIRE) {
					fireQueue.offer(new Position(x, y, 0));
					fireTime[x][y] = 0;
				}
			}
		}

		// 1. 불의 BFS - 각 칸에 불이 도달하는 시간 계산
		bfsFire(fireQueue);

		// 2. 지훈이의 BFS - 불보다 빨리 도달할 수 있는 경로 찾기
		int result = bfsJihun(startX, startY);

		System.out.println(result == INF ? "IMPOSSIBLE" : result);
	}

	private static void bfsFire(Queue<Position> queue) {
		while (!queue.isEmpty()) {
			Position cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + DX[i];
				int ny = cur.y + DY[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (maps[nx][ny] == WALL || fireTime[nx][ny] != INF) {
					continue;
				}

				fireTime[nx][ny] = cur.cnt + 1;
				queue.offer(new Position(nx, ny, cur.cnt + 1));
			}
		}
	}

	private static int bfsJihun(int x, int y) {
		Queue<Position> queue = new LinkedList<>();
		queue.offer(new Position(x, y, 0));
		jihunTime[x][y] = 0;

		while (!queue.isEmpty()) {
			Position cur = queue.poll();

			if (cur.x == 0 || cur.x == N - 1 || cur.y == 0 || cur.y == M - 1) {
				return cur.cnt + 1;
			}

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + DX[i];
				int ny = cur.y + DY[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) {
					continue;
				}

				if (maps[nx][ny] == WALL || jihunTime[nx][ny] != INF) {
					continue;
				}

				// 지훈이가 도착하는 시간이 불이 도착하는 시간보다 빨라야 함
				if (cur.cnt + 1 < fireTime[nx][ny]) {
					jihunTime[nx][ny] = cur.cnt + 1;
					queue.offer(new Position(nx, ny, cur.cnt + 1));
				}
			}
		}

		return INF;
	}

	private static class Position {
		int x;
		int y;
		int cnt;

		public Position(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}
}
