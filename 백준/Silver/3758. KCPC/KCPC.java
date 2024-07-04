import java.io.*;
import java.util.*;

public class Main {
    static class Team {
        int id, submitNum, lastSubmit, totalScore;
        int[] scoreList;

        Team(int id, int k) {
            this.id = id;
            this.scoreList = new int[k + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            Team[] teams = new Team[n];
            for (int i = 0; i < n; i++) {
                teams[i] = new Team(i + 1, k);
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int teamID = Integer.parseInt(st.nextToken()) - 1;
                int problemNum = Integer.parseInt(st.nextToken());
                int score = Integer.parseInt(st.nextToken());

                Team team = teams[teamID];
                team.scoreList[problemNum] = Math.max(score, team.scoreList[problemNum]);
                team.submitNum++;
                team.lastSubmit = i;
            }

            for (Team team : teams) {
                team.totalScore = Arrays.stream(team.scoreList).sum();
            }

            Arrays.sort(teams, (a, b) -> {
                if (a.totalScore != b.totalScore) return b.totalScore - a.totalScore;
                if (a.submitNum != b.submitNum) return a.submitNum - b.submitNum;
                return a.lastSubmit - b.lastSubmit;
            });

            for (int i = 0; i < n; i++) {
                if (teams[i].id == t) {
                    sb.append(i + 1).append('\n');
                    break;
                }
            }
        }

        System.out.print(sb);
    }
}
