def twoSum(nums, target):
    n = len(nums)
    for i in range(n):
        for j in range(i + 1, n):
            if nums[i] + nums[j] == target:
                return True
    return False

print(twoSum(nums=[4,1,3,5,6,8,15,16], target=14))
