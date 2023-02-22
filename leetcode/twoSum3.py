def twoSum(nums, target):
    ditc = {}
    for key in nums:
        ditc[key] = ""
    print(ditc)

    for key in nums:
        need_Num = target - key
        if need_Num in ditc:
            return True
    return False

print(twoSum(nums=[4,1,3,5,6,8,15,16], target=14))
