def fibo(n):
    if n == 1 or n == 2 or n == 0:
        return 1
    else:
        return fibo(n - 1) + fibo(n - 2)

print(fibo(4))
print(fibo(8))
print(fibo(9))
print(fibo(10))