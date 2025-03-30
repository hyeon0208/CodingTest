import java.util.Arrays;
import java.util.Comparator;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, Comparator.comparingInt(a -> a[1]));

        int count = 1;
        int lastCameraPosition = routes[0][1];

        System.out.println(Arrays.deepToString(routes));

        for (int i = 1; i < routes.length; i++) {
            if (routes[i][0] > lastCameraPosition) {
                count++;
                lastCameraPosition = routes[i][1];
            }
        }

        return count;
    }
}