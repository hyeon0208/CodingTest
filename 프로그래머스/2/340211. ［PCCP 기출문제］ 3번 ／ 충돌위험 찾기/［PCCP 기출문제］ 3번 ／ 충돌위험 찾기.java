import java.util.*;

class Solution {
    
    private static Queue<Point>[] robotPaths;
    private static int robotCount;
    private static int dangerCount;

    public static int solution(int[][] points, int[][] routes) {
        robotCount = routes.length;
        dangerCount = 0;

        // 각 로봇의 경로를 저장할 큐 배열 초기화
        robotPaths = new LinkedList[robotCount];
        for (int i = 0; i < robotCount; i++) {
            robotPaths[i] = new LinkedList<>();
        }
        calculateRobotPaths(points, routes);
        simulateAndCountDangers();

        return dangerCount;
    }

    /**
     * 각 로봇의 경로를 계산하여 큐에 저장
     */
    private static void calculateRobotPaths(int[][] points, int[][] routes) {
        for (int robotIndex = 0; robotIndex < robotCount; robotIndex++) {
            int[] route = routes[robotIndex];

            // 시작 위치 설정 (r은 y, c는 x로 매핑)
            int x = points[route[0] - 1][1];  // c 좌표
            int y = points[route[0] - 1][0];  // r 좌표

            // 시작 위치를 경로에 추가
            robotPaths[robotIndex].offer(new Point(x, y));

            // 경로상의 각 목표 지점으로 이동
            for (int pointIndex = 1; pointIndex < route.length; pointIndex++) {
                int targetX = points[route[pointIndex] - 1][1];  // 목표 c 좌표
                int targetY = points[route[pointIndex] - 1][0];  // 목표 r 좌표
                moveToTarget(robotIndex, x, y, targetX, targetY);

                // 현재 위치 업데이트
                x = targetX;
                y = targetY;
            }
        }
    }

    /**
     * 현재 위치에서 목표 위치로 이동하는 경로를 계산
     * 문제 조건에 따라 r 좌표(y)를 먼저 이동
     */
    private static void moveToTarget(int robotIndex, int currentX, int currentY, int targetX, int targetY) {
        // r 좌표(y) 이동
        while (currentY != targetY) {
            if (currentY < targetY) {
                currentY++;
            } else {
                currentY--;
            }
            robotPaths[robotIndex].offer(new Point(currentX, currentY));
        }

        // c 좌표(x) 이동
        while (currentX != targetX) {
            if (currentX < targetX) {
                currentX++;
            } else {
                currentX--;
            }
            robotPaths[robotIndex].offer(new Point(currentX, currentY));
        }
    }

    /**
     * 로봇들의 이동을 시뮬레이션하고 위험 상황 카운트
     */
    private static void simulateAndCountDangers() {
        int completedRobots = 0;
        int maxCoordinate = 100;  // 좌표의 최대값

        // 모든 로봇이 경로를 완료할 때까지 시뮬레이션
        while (completedRobots < robotCount) {
            // 현재 시간의 로봇 위치를 기록할 맵
            int[][] maps = new int[maxCoordinate + 1][maxCoordinate + 1];
            completedRobots = 0;

            // 각 로봇의 다음 위치 처리
            for (int i = 0; i < robotCount; i++) {
                // 로봇의 경로가 끝났으면 완료 카운트 증가
                if (robotPaths[i].isEmpty()) {
                    completedRobots++;
                    continue;
                }

                // 로봇의 다음 위치를 꺼내서 맵에 기록
                Point nextPosition = robotPaths[i].poll();
                maps[nextPosition.x][nextPosition.y]++;
            }

            // 위험 상황 카운트 (2대 이상의 로봇이 같은 위치에 있는 경우)
            countDangerousPositions(maps);
        }
    }

    /**
     * 현재 시간의 맵에서 위험 상황 카운트
     */
    private static void countDangerousPositions(int[][] maps) {
        for (int x = 0; x < maps.length; x++) {
            for (int y = 0; y < maps.length; y++) {
                if (maps[x][y] > 1) {
                    dangerCount++;
                }
            }
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}