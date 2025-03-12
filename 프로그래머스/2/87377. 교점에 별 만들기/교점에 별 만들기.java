import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        // 정수 좌표 교점을 저장할 집합
        Set<Point> points = new HashSet<>();
        
        // 모든 직선 쌍에 대해 교점 계산
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                Point intersection = findIntersection(line[i], line[j]);
                if (intersection != null) {
                    points.add(intersection);
                }
            }
        }
        
        // 교점들의 x, y 최소/최대값 구하기
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;
        
        for (Point p : points) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
            maxX = Math.max(maxX, p.x);
            maxY = Math.max(maxY, p.y);
        }
        
        // 2차원 배열 크기 계산 (가로: maxX - minX + 1, 세로: maxY - minY + 1)
        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);
        
        // 결과 배열 초기화 ('.'으로 채움)
        char[][] array = new char[height][width];
        for (int i = 0; i < height; i++) {
            Arrays.fill(array[i], '.');
        }
        
        // 별 표시하기
        for (Point p : points) {
            // 좌표계 변환: (x, y) -> (x - minX, maxY - y)
            int x = (int) (p.x - minX);
            int y = (int) (maxY - p.y);
            array[y][x] = '*';
        }
        
        // char[][] 배열을 String[] 배열로 변환
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(array[i]);
        }
        
        return answer;
    }
    
    // 두 직선의 교점 계산 함수
    private Point findIntersection(int[] line1, int[] line2) {
        long A = line1[0], B = line1[1], E = line1[2];
        long C = line2[0], D = line2[1], F = line2[2];
        
        // 분모 계산: AD - BC
        long denominator = A * D - B * C;
        
        // 분모가 0이면 두 직선은 평행하거나 일치하므로 교점 없음
        if (denominator == 0) {
            return null;
        }
        
        // 분자 계산: BF - ED, AF - EC
        long numerator1 = B * F - E * D;
        long numerator2 = E * C - A * F;
        
        // 두 분자가 모두 분모로 나누어 떨어지는지 확인 (정수 좌표인지)
        if (numerator1 % denominator != 0 || numerator2 % denominator != 0) {
            return null;
        }
        
        // 교점 좌표 계산
        long x = numerator1 / denominator;
        long y = numerator2 / denominator;
        
        return new Point(x, y);
    }
    
    static class Point {
        long x, y;
        
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
        
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
        
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}