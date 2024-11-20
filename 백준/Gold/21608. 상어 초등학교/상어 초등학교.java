import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private static int N;
    private static int[][] classroom;
    private static Map<Integer, List<Integer>> prefer;
    private static int[] DX = {-1, 1, 0, 0};
    private static int[] DY = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        classroom = new int[N][N];
        prefer = new HashMap<>();
        int total = N * N;

        for (int x = 0; x < total; x++) {
            String[] input = br.readLine().split(" ");
            int student = Integer.parseInt(input[0]);
            List<Integer> likes = new ArrayList<>();
            for (int y = 1; y <= 4; y++) {
                likes.add(Integer.parseInt(input[y]));
            }
            prefer.put(student, likes);
            setSeat(student);
        }
        
        System.out.println(calculateSatisfaction());
    }

    static void setSeat(int student) {
        int maxLike = -1;
        int maxEmpty = -1;
        int resultX = 0;
        int resultY = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] == 0) {
                    AdjacentInfo info = getAdjacentInfo(i, j, student);

                    // 조건 1: 좋아하는 학생이 더 많은 경우
                    if (info.likeCnt > maxLike) {
                        maxLike = info.likeCnt;
                        maxEmpty = info.empty;
                        resultX = i;
                        resultY = j;
                    }
                    // 조건 2: 좋아하는 학생 수가 같은 경우, 비어있는 칸이 더 많은 곳
                    else if (info.likeCnt == maxLike && info.empty > maxEmpty) {
                        maxEmpty = info.empty;
                        resultX = i;
                        resultY = j;
                    }
                    // 조건 3: 행과 열이 가장 작은 칸
                    else if (info.likeCnt == maxLike && info.empty == maxEmpty) {
                        if (i < resultX) {
                            resultX = i;
                            resultY = j;
                        }
                        if (i == resultX && j < resultY) {
                            resultY = j;
                        }
                    }
                }
            }
        }

        classroom[resultX][resultY] = student;
    }

    static AdjacentInfo getAdjacentInfo(int x, int y, int student) {
        int empty = 0;
        int likeCnt = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + DX[i];
            int ny = y + DY[i];

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (classroom[nx][ny] == 0) {
                    empty++;
                }
                if (prefer.get(student).contains(classroom[nx][ny])) {
                    likeCnt++;
                }
            }
        }

        return new AdjacentInfo(likeCnt, empty);
    }

    static int calculateSatisfaction() {
        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int student = classroom[i][j];
                if (student != 0 && prefer.containsKey(student)) {
                    int cnt = 0;

                    for (int k = 0; k < 4; k++) {
                        int nx = i + DX[k];
                        int ny = j + DY[k];

                        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                            if (prefer.get(student).contains(classroom[nx][ny])) {
                                cnt++;
                            }
                        }
                    }

                    if (cnt > 0) {
                        total += (int) Math.pow(10, cnt - 1);
                    }
                }
            }
        }

        return total;
    }

    private static class AdjacentInfo {
        int likeCnt;
        int empty;

        AdjacentInfo(int likeCnt, int empty) {
            this.likeCnt = likeCnt;
            this.empty = empty;
        }
    }
}
