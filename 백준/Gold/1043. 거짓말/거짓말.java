import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static int N;
    private static int M;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int truthKnowPersonCnt = Integer.parseInt(st.nextToken());
        if (truthKnowPersonCnt == 0) {
            System.out.println(M);
            return;
        }
        int truthRoot = 0;
        for (int i = 0; i < truthKnowPersonCnt; i++) {
            int person = Integer.parseInt(st.nextToken());
            if (i == 0) {
                truthRoot = person;
            } else {
                union(truthRoot, person); // 진실을 아는 사람들을 그룹으로 묶음
            }
        }

        List<List<Integer>> parties = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int partyCnt = Integer.parseInt(st.nextToken());
            List<Integer> party = new ArrayList<>();

            int firstPerson = 0;
            for (int j = 0; j < partyCnt; j++) {
                int person = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    firstPerson = person;
                } else {
                    union(firstPerson, person); // 파티에 참가한 사람들을 그룹으로 묶음
                }
                party.add(person);
            }
            parties.add(party);
        }

        int answer = M;
        for (List<Integer> party : parties) {
            for (int person : party) {
                // 파티 참가자 중 한 명이라도 진실을 아는 그룹에 속하면
                if (find(person) == find(truthRoot)) {
                    answer--;
                    break;
                }
            }
        }

        System.out.println(answer);
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]); // 경로 압축
    }

    public static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if (parentA == parentB) {
            return;
        }

        if (parentA < parentB) {
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }
}
