# -*- coding: utf-8 -*-
def lcs(nums):
    if len(nums) == 0:
            return 0

    long_Len = 1
    dict = set(nums)

    for i in dict:
        count = 0
        print(i - 1)
        if i - 1 not in dict: # 만약 최초로 시작하는 값(i - 1)이 없다면 while문 실행
            next = i + 1
            count += 1
            while next in dict:
                next += 1
                count += 1
            long_Len = max(long_Len, count)
    return long_Len

print(lcs(nums = [100, 4, 200, 1, 3, 2]))
print(lcs(nums = [0,3,7,2,5,8,4,6,0,1]))