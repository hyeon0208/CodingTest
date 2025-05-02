class Solution {

    private static final int[] DX = {1,0,0,-1};
    private static final int[] DY = {0,-1,1,0};
    private static final String[] DIR = {"d","l","r","u"};

    private static char[][] map;
    private static String result = "";
    private static boolean found = false;
    private static boolean[][][] visited;

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = '.';
            }
        }
        map[x - 1][y - 1] = 'S';
        map[r - 1][c - 1] = 'E';

        visited = new boolean[n][m][k+1];

        dfs(x - 1, y - 1, k, r - 1, c - 1, new StringBuilder());

        if (result.isEmpty()) {
            return "impossible";
        }
        return result;
    }

    private static void dfs(int x, int y, int k, int r, int c, StringBuilder sb) {
        if (found) {
            return;
        }
        if (map[x][y] == 'E' && k == 0) {
            result = sb.toString();
            found = true;
            return;
        }
        if (k <= 0) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (0 <= nx && nx < map.length && 0 <= ny && ny < map[0].length && !visited[nx][ny][k-1]) {
                visited[nx][ny][k-1] = true;
                sb.append(DIR[i]);
                dfs(nx, ny, k - 1, r, c, sb);
                if (found) {
                    return;
                }
                sb.deleteCharAt(sb.length() - 1);
            }
        }
    }
}