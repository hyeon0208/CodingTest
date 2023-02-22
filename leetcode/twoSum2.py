# -*- coding: utf-8 -*-

def twoSum2(nums, target):
    # 정렬 - O(nlogn)
    nums.sort()
    left = 0
    right = len(nums) - 1

    # 투포인터 - O(n)
    while left < right:
        if nums[left] + nums[right] > target:
            right -= 1
        elif nums[left] + nums[right] < target:
            left += 1
        elif nums[left] + nums[right] == target:
            return True
    return False

print(twoSum2(nums=[1, 2, 3, 5, 7], target=7))
