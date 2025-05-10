import java.util.*;

class Solution {
    
    private static final int WALL = 1;
    private static final int[] DX = {0, 1, 0, -1}; // 우, 하, 좌, 상
    private static final int[] DY = {1, 0, -1, 0};
    
    public static int solution(int[][] board) {
        int n = board.length;

        Set<String> visited = new HashSet<>();
        Queue<Robot> queue = new LinkedList<>();
        Robot start = new Robot(0, 0, 0, 1, 0);
        queue.offer(start);
        visited.add(getStateKey(start));

        while (!queue.isEmpty()) {
            Robot cur = queue.poll();

            if ((cur.x1 == n-1 && cur.y1 == n-1) || (cur.x2 == n-1 && cur.y2 == n-1)) {
                return cur.time;
            }

            // 1. 상하좌우 이동
            for (int i = 0; i < 4; i++) {
                int nx1 = cur.x1 + DX[i];
                int ny1 = cur.y1 + DY[i];
                int nx2 = cur.x2 + DX[i];
                int ny2 = cur.y2 + DY[i];

                if (isValid(nx1, ny1, board) && isValid(nx2, ny2, board)) {
                    Robot next = new Robot(nx1, ny1, nx2, ny2, cur.time + 1);
                    String key = getStateKey(next);

                    if (!visited.contains(key)) {
                        queue.offer(next);
                        visited.add(key);
                    }
                }
            }

            // 2. 회전 처리
            // 가로 방향인 경우 (x좌표가 같음)
            if (cur.x1 == cur.x2) {
                // 위쪽으로 회전
                checkRotateHorizontal(cur, board, -1, visited, queue);
                // 아래쪽으로 회전
                checkRotateHorizontal(cur, board, 1, visited, queue);
            }
            // 세로 방향인 경우 (y좌표가 같음)
            else if (cur.y1 == cur.y2) {
                // 왼쪽으로 회전
                checkRotateVertical(cur, board, -1, visited, queue);
                // 오른쪽으로 회전
                checkRotateVertical(cur, board, 1, visited, queue);
            }
        }

        return -1;
    }

    private static void checkRotateHorizontal(Robot robot, int[][] board, int direction, Set<String> visited, Queue<Robot> queue) {
        // 첫 번째 칸을 축으로 회전
        int pivotX = robot.x1;
        int pivotY = robot.y1;
        int checkUP = robot.x1 + direction;
        int checkDiagonal = robot.x2 + direction;
        if (isValid(checkUP, pivotY, board) && isValid(checkDiagonal, robot.y2, board)) {
            Robot next = new Robot(pivotX, pivotY, checkUP, pivotY, robot.time + 1);
            String key = getStateKey(next);
            if (!visited.contains(key)) {
                queue.offer(next);
                visited.add(key);
            }
        }

        // 두 번째 칸을 축으로 회전
        pivotX = robot.x2;
        pivotY = robot.y2;
        checkUP = robot.x2 + direction;
        checkDiagonal = robot.x1 + direction;
        if (isValid(checkUP, pivotY, board) && isValid(checkDiagonal, robot.y1, board)) {
            Robot next = new Robot(checkUP, pivotY, pivotX, pivotY, robot.time + 1);
            String key = getStateKey(next);
            if (!visited.contains(key)) {
                queue.offer(next);
                visited.add(key);
            }
        }
    }

    private static void checkRotateVertical(Robot robot, int[][] board, int direction, Set<String> visited, Queue<Robot> queue) {
        // 첫 번째 칸을 축으로 회전
        int pivotX = robot.x1;
        int pivotY = robot.y1;
        int checkUP = robot.y1 + direction;
        int checkDiagonal = robot.y2 + direction;
        if (isValid(pivotX, checkUP, board) && isValid(robot.x2, checkDiagonal, board)) {
            Robot next = new Robot(pivotX, pivotY, pivotX, checkUP, robot.time + 1);
            String key = getStateKey(next);
            if (!visited.contains(key)) {
                queue.offer(next);
                visited.add(key);
            }
        }

        // 두 번째 칸을 축으로 회전
        pivotX = robot.x2;
        pivotY = robot.y2;
        checkUP = robot.y2 + direction;
        checkDiagonal = robot.y1 + direction;
        if (isValid(pivotX, checkUP, board) && isValid(robot.x1, checkDiagonal, board)) {
            Robot next = new Robot(pivotX, checkUP, pivotX, pivotY, robot.time + 1);
            String key = getStateKey(next);
            if (!visited.contains(key)) {
                queue.offer(next);
                visited.add(key);
            }
        }
    }

    private static String getStateKey(Robot robot) {
        // 로봇의 두 부분 위치를 정렬하여 일관된 키를 생성
        if (robot.x1 < robot.x2 || robot.y1 < robot.y2) {
            return robot.x1 + "," + robot.y1 + "," + robot.x2 + "," + robot.y2;
        } else {
            return robot.x2 + "," + robot.y2 + "," + robot.x1 + "," + robot.y1;
        }
    }

    private static boolean isValid(int x, int y, int[][] board) {
        int n = board.length;
        return x >= 0 && x < n && y >= 0 && y < n && board[x][y] != WALL;
    }

    private static class Robot {
        int x1, y1;
        int x2, y2;
        int time;

        public Robot(int x1, int y1, int x2, int y2, int time) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.time = time;
        }
    }
}