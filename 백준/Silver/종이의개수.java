import java.io.*;
import java.util.*;
 
public class Main {
	public static int[][] board;
	public static int GRAY = 0;	
	public static int WHITE = 0;
	public static int BLACK = 0;
 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		board = new int[N][N];
        
		for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
 
		partition(0, 0, N);
 
		System.out.println(GRAY);
		System.out.println(WHITE);
		System.out.println(BLACK);
	}
 
	
	public static void partition(int row, int col, int size) {
		if (colorCheck(row, col, size)) {
			if (board[row][col] == -1) { 
				GRAY++;
			}
			if (board[row][col] == 0) {
				WHITE++;
			}
			if (board[row][col] == 1) {
				BLACK++;
			}
 
			return;
		}
 
		int newSize = size / 3;
		
		partition(row, col, newSize);								// 왼쪽 위
		partition(row, col + newSize, newSize);						// 중앙 위
		partition(row, col + 2 * newSize, newSize);					// 오른쪽 위
		
		partition(row + newSize, col, newSize);						// 왼쪽 중간
		partition(row + newSize, col + newSize, newSize);			// 중앙 중간
		partition(row + newSize, col + 2 * newSize, newSize);		// 오른쪽 중간
		
		partition(row + 2 * newSize, col, newSize);					// 왼쪽 아래
		partition(row + 2 * newSize, col + newSize, newSize);		// 중앙 아래
		partition(row + 2 * newSize, col + 2 * newSize, newSize);	// 오른쪽 아래
 
	}
 
	public static boolean colorCheck(int row, int col, int size) {
		int color = board[row][col];
 
		for (int i = row; i < row + size; i++) {
			for (int j = col; j < col + size; j++) {
				if (color != board[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
}
