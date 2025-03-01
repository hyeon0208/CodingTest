import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] servers = new int[24];

        for (int i = 0; i < 24; i++) {
            int server = players[i] / m;

            if (0 == server) {
                continue;
            }

            if (servers[i] >= server) {
                continue;
            }

            int add = server - servers[i];
            answer += add;

            int end = Math.min(24, i + k);

            for (int j = i; j < end; j++) {
                servers[j] += add;
            }
        }

        return answer;
    }
}