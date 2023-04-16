class Solution:
    def uniquePaths(self, m: int, n: int) -> int:
        dp = {}
        for y in range(m):
            dp[(0,y)] = 1
        for x in range(n):
            dp[(x,0)] = 1
            
        for x in range(1,n):
            for y in range(1,m):
                dp[(x,y)] = dp[(x-1,y)] + dp[(x,y-1)] 
                
        return dp[(n-1,m-1)]