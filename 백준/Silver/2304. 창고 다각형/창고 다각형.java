import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Column> columns = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            columns.add(new Column(L, H));
        }
        columns.sort(Comparator.comparingInt(c -> c.length));

        int area = 0;
        int maxHeightIndex = 0;
        for (int i = 0; i < N; i++) {
            if (columns.get(maxHeightIndex).height < columns.get(i).height) {
                maxHeightIndex = i;
            }
        }

        int otherHeightIndex = 0;
        for (int i = 0; i <= maxHeightIndex; i++) {
            if (columns.get(otherHeightIndex).height <= columns.get(i).height) {
                area += (columns.get(i).length - columns.get(otherHeightIndex).length) * columns.get(otherHeightIndex).height;
                otherHeightIndex = i;
            }
        }
        
        otherHeightIndex = N - 1;
        for (int i = N - 1; i >= maxHeightIndex; i--) {
            if (columns.get(otherHeightIndex).height <= columns.get(i).height) {
                area += (columns.get(otherHeightIndex).length - columns.get(i).length) * columns.get(otherHeightIndex).height;
                otherHeightIndex = i;
            }
        }
        area += columns.get(maxHeightIndex).height;

        System.out.println(area);
    }

    private static class Column {
        int length;
        int height;

        public Column(int length, int height) {
            this.length = length;
            this.height = height;
        }
    }
}
