import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    private static final int[][] arr = new int[100][100];

    private static int rowSize = 3;
    private static int colSize = 3;
    private static int r;
    private static int c;
    private static int k;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken()) - 1;
        c = Integer.parseInt(st.nextToken()) - 1;
        k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solve());
    }

    private static int solve() {
        int time = 0;

        while (time <= 100) {
            if (arr[r][c] == k) {
                return time;
            }

            if (rowSize >= colSize) {
                operationR();
            } else {
                operationC();
            }
            time++;
        }

        return -1;
    }

    private static void operationR() {
        int maxCol = 0;

        for (int i = 0; i < rowSize; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int j = 0; j < colSize; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                countMap.put(arr[i][j], countMap.getOrDefault(arr[i][j], 0) + 1);
            }

            List<NumberCount> counts = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                counts.add(new NumberCount(entry.getKey(), entry.getValue()));
            }
            Collections.sort(counts);

            Arrays.fill(arr[i], 0);
            int idx = 0;
            for (NumberCount nc : counts) {
                if (idx >= 100) {
                    break;
                }
                arr[i][idx++] = nc.getNumber();
                arr[i][idx++] = nc.getCount();
            }

            maxCol = Math.max(maxCol, idx);
        }

        colSize = maxCol;
    }

    private static void operationC() {
        int maxRow = 0;

        for (int i = 0; i < colSize; i++) {
            Map<Integer, Integer> countMap = new HashMap<>();
            for (int j = 0; j < rowSize; j++) {
                if (arr[j][i] == 0) {
                    continue;
                }
                countMap.put(arr[j][i], countMap.getOrDefault(arr[j][i], 0) + 1);
                arr[j][i] = 0;
            }

            List<NumberCount> counts = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                counts.add(new NumberCount(entry.getKey(), entry.getValue()));
            }
            Collections.sort(counts);

            int idx = 0;
            for (NumberCount nc : counts) {
                if (idx >= 100) {
                    break;
                }
                arr[idx++][i] = nc.getNumber();
                arr[idx++][i] = nc.getCount();
            }

            maxRow = Math.max(maxRow, idx);
        }

        rowSize = maxRow;
    }

    private static class NumberCount implements Comparable<NumberCount> {
        private final int number;
        private final int count;

        public NumberCount(int number, int count) {
            this.number = number;
            this.count = count;
        }

        public int getNumber() {
            return number;
        }

        public int getCount() {
            return count;
        }

        @Override
        public int compareTo(NumberCount other) {
            if (this.count == other.count) {
                return this.number - other.number;
            }
            return this.count - other.count;
        }
    }
}
