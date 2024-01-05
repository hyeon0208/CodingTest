import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int population[];
    static List<ArrayList<Integer>> map;
    static boolean sectionA[];
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        result = Integer.MAX_VALUE;
        population = new int[N];
        sectionA = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }
        map = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int count = Integer.parseInt(st.nextToken());
            for (int j = 0; j < count; j++) {
                map.get(i).add(Integer.parseInt(st.nextToken()) - 1);
            }
        }

        dfs(0);
        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        System.out.println(result);
    }

    private static void dfs(int depth) {
        if (depth == N) {
            List<Integer> aSections = new ArrayList<>();
            List<Integer> bSections = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                if (sectionA[i]) {
                    aSections.add(i);
                } else {
                    bSections.add(i);
                }
            }
            if (aSections.size() == 0 || bSections.size() == 0) {
                return;
            }
            if (isValidSections(aSections) && isValidSections(bSections)) {
                calculatePopulationDiff();
            }
            return;
        }

        sectionA[depth] = true;
        dfs(depth + 1); // depth를 A 구역으로 설정하고 탐색
        sectionA[depth] = false;
        dfs(depth + 1); // depth를 B 구역으로 설정하고 탐색
    }

    private static boolean isValidSections(List<Integer> sections) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[N];
        visited[sections.get(0)] = true;
        q.offer(sections.get(0));

        int count = 1;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < map.get(cur).size(); i++) {
                int index = map.get(cur).get(i);
                if (sections.contains(index) && !visited[index]) {
                    q.offer(index);
                    visited[index] = true;
                    count++;
                }
            }
        }
        if (count == sections.size()) {
            return true;
        } else {
            return false;
        }
    }

    private static void calculatePopulationDiff() {
        int a = 0;
        int b = 0;
        for (int i = 0; i < N; i++) {
            if (sectionA[i]) {
                a += population[i];
            } else {
                b += population[i];
            }
        }
        result = Math.min(result, Math.abs(a - b));
    }
}