import sys
input = sys.stdin.readline

N = int(input())
N_nums = list(map(int, input().split()))
N_nums.sort() # 이분 탐색은 탐색하고자 하는 리스트가 정렬되어 있어야 한다.

M = int(input())
M_nums = list(map(int, input().split()))

def binarySearch(arr, target):
    low = 0
    high = len(arr) - 1

    while low <= high:
        mid = low + (high - low) // 2

        if arr[mid] == target:
            return True

        if target < arr[mid]:
            high = mid - 1
        if target > arr[mid]:
            low = mid + 1
    
    return False

for i in range(M):
    if binarySearch(N_nums, M_nums[i]):
        print(1)
    else:
        print(0)