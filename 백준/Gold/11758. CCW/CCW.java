import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException { // https://www.acmicpc.net/problem/11758
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Position[] positions = new Position[3];
		for (int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			positions[i] = new Position(x, y);
		}

		System.out.println(ccw(positions[0], positions[1], positions[2]));
	}

	private static int ccw(Position p1, Position p2, Position p3) {
		// p1 -> p2 벡터 : (p2.x - p1.x, p2.y - p1.y)
		// p1 -> p3 벡터 : (p3.x - p1.x, p3.y - p1.y)
		// 외적 : (p2.x - p1.x) * (p3.y - p1.y) - (p2.y - p1.y) * (p3.x - p1.x)
		long cross = (long)(p2.x - p1.x) * (p3.y - p1.y) - (long)(p2.y - p1.y) * (p3.x - p1.x);

		if (cross > 0) {
			return 1;  // 반시계방향
		} else if (cross < 0) {
			return -1; // 시계방향
		} else {
			return 0;  // 일직선상
		}
	}

	private static class Position {
		int x, y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}