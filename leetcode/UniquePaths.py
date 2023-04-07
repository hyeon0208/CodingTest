class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        grid = [[0]*m for _ in range(n)]
        grid[0][0] = 1
        for i in range(n):
            for j in range(m):
                if 0<=i-1<n:
                    grid[i][j] += grid[i-1][j]
                if 0<=j-1<m:
                    grid[i][j] += grid[i][j-1]
        return grid[-1][-1]