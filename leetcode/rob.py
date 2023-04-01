class Solution(object):
    def rob(self, nums):
        n = len(nums)
        memo = [-1] * n

        def dfs(n):
            if n == 0:
                return nums[0]
            if n == 1:
                return max(nums[n], nums[n - 1])

            if memo[n] == -1:
                memo[n] = max(dfs(n - 1), dfs(n - 2) + nums[n])
            return memo[n]

        dfs(n - 1)