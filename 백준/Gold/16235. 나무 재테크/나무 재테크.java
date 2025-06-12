import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N, M, K;
    private static int[][] A; // 겨울에 추가될 양분
    private static int[][] ground; // 현재 땅의 양분
    private static List<Tree> trees;

    private static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        ground = new int[N][N];
        trees = new ArrayList<>();

        // 2차원 배열 초기화
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] = 5; // 초기 양분은 5
            }
        }

        // 겨울에 추가될 양분 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 나무 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        // K년간 시뮬레이션
        while (K-- > 0) {
            springAndSummer();
            fall();
            winter();
        }

        System.out.println(trees.size());
    }

    // 봄과 여름을 함께 처리
    private static void springAndSummer() {
        trees.sort(Comparator.comparingInt(a -> a.age));

        List<Tree> alive = new ArrayList<>();
        int[][] deadNutrition = new int[N][N];

        for (Tree tree : trees) {
            // 봄: 양분을 먹을 수 있는지 확인
            if (ground[tree.x][tree.y] >= tree.age) {
                ground[tree.x][tree.y] -= tree.age; // 양분 소모
                alive.add(new Tree(tree.x, tree.y, tree.age + 1)); // 나이 증가
            } else {
                // 여름: 죽은 나무가 양분으로 변함
                deadNutrition[tree.x][tree.y] += tree.age / 2;
            }
        }

        // 죽은 나무들의 양분을 땅에 추가
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += deadNutrition[i][j];
            }
        }

        trees = alive;
    }

    // 가을: 번식
    private static void fall() {
        List<Tree> newTrees = new ArrayList<>();

        for (Tree tree : trees) {
            // 나이가 5의 배수인 나무만 번식
            if (tree.age % 5 == 0) {
                // 8방향으로 번식
                for (int d = 0; d < 8; d++) {
                    int nx = tree.x + dx[d];
                    int ny = tree.y + dy[d];

                    // 범위 체크
                    if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                        newTrees.add(new Tree(nx, ny, 1));
                    }
                }
            }
        }

        trees.addAll(newTrees);
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ground[i][j] += A[i][j];
            }
        }
    }

    private static class Tree {
        int x, y, age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }
}
