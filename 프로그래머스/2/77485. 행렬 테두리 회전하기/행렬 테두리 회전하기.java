  import java.util.*;

  class Solution {
      public int[] solution(int rows, int columns, int[][] queries) {
          List<Integer> answer = new ArrayList<>();
          int[][] map = new int[rows][columns];

          int n = 1;
          for (int x = 0; x < rows; x++) {
              for (int y = 0; y < columns; y++) {
                  map[x][y] = n;
                  n++;
              }
          }

          for (int[] query : queries) {
              int x1 = query[0];
              int y1 = query[1];
              int x2 = query[2];
              int y2 = query[3];

              int start = map[x1 - 1][y1 - 1];
              int minNum = start;

              // 왼쪽 변 (아래에서 위로)
              for (int i = x1 - 1; i < x2 - 1; i++) {
                  int temp = map[i + 1][y1 - 1];
                  map[i][y1 - 1] = temp;
                  minNum = Math.min(minNum, temp);
              }

              // 아래 변 (오른쪽에서 왼쪽으로)
              for (int i = y1 - 1; i < y2 - 1; i++) {
                  int temp = map[x2 - 1][i + 1];
                  map[x2 - 1][i] = temp;
                  minNum = Math.min(minNum, temp);
              }

              // 오른쪽 변 (위에서 아래로)
              for (int i = x2 - 1; i > x1 - 1; i--) {
                  int temp = map[i - 1][y2 - 1];
                  map[i][y2 - 1] = temp;
                  minNum = Math.min(minNum, temp);
              }

              // 위 변 (왼쪽에서 오른쪽으로)
              for (int i = y2 - 1; i > y1 - 1; i--) {
                  int temp = map[x1 - 1][i - 1];
                  map[x1 - 1][i] = temp;
                  minNum = Math.min(minNum, temp);
              }

              // 회전 완성
              map[x1 - 1][y1] = start;
              answer.add(minNum);
          }

          return answer.stream().mapToInt(Integer::intValue).toArray();
      }
  }

