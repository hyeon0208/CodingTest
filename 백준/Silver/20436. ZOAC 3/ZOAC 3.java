import java.io.*;
import java.util.*;

public class Main {
	static char A;
	static char B;
	static int dis;
	
	static char[][] leftarr = {{'q','w','e','r','t'},{'a','s','d','f','g'},{'z','x','c','v'}};
	static char[][] rightarr = {{'.','y','u','i','o','p'},{'.','h','j','k','l'},{'b','n','m'}};
	
	public static void main(String[] args) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stf = new StringTokenizer(br.readLine());
		A = (stf.nextToken().charAt(0));
		B = (stf.nextToken().charAt(0));
		
		int leftstart_y = -1;
		int leftstart_x = -1;
		int rightstart_y = -1;
		int rightstart_x = -1;
		String inputstr = br.readLine();
		for(int i = 0; i < leftarr.length; i++) {
			for(int j = 0; j < leftarr[i].length; j++) {
				char c = leftarr[i][j];
				if(A==c) {
					leftstart_y = i;
					leftstart_x = j;
				}
			}
		}
		for(int i = 0; i < rightarr.length; i++) {
			for(int j = 0; j < rightarr[i].length; j++) {
				char c = rightarr[i][j];
				if(B==c) {
					rightstart_y = i;
					rightstart_x = j;
				}
			}
		}
		for(int k = 0; k < inputstr.length(); k++) {
			char cur = inputstr.charAt(k);
			int leftmove_y = -1;
			int leftmove_x = -1;
			int rightmove_y = -1;
			int rightmove_x = -1;
			for(int i = 0; i < leftarr.length; i++) {
				for(int j = 0; j < leftarr[i].length; j++) {
					char c = leftarr[i][j];
					if(cur==c) {
						leftmove_y = i;
						leftmove_x = j;
					}
				}
			}
			if(leftmove_y!=-1 && leftmove_x!=-1) {
				dis += Math.abs(leftstart_y-leftmove_y) + Math.abs(leftstart_x-leftmove_x);
				dis++;
				leftstart_y = leftmove_y;
				leftstart_x = leftmove_x;
				continue;
			}		
			for(int i = 0; i < rightarr.length; i++) {
				for(int j = 0; j < rightarr[i].length; j++) {
					char c = rightarr[i][j];
					if(cur==c) {
						rightmove_y = i;
						rightmove_x = j;
					}
				}
			}
			if(rightmove_y!=-1 && rightmove_x!=-1) {
				dis += Math.abs(rightstart_y-rightmove_y) + Math.abs(rightstart_x-rightmove_x);
				dis++;
				rightstart_y = rightmove_y;
				rightstart_x = rightmove_x;
				continue;
			}	
		}
        System.out.println(dis);
	}
}