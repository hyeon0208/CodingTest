# -*- coding: utf-8 -*-

class Solution:
    def isValid(self, s):
        stack = []
        for c in s:
            if c == "(":
                stack.append(")")
            elif c == "{":
                stack.append("}")
            elif c == "[":
                stack.append("]")
            # stack이 비었거나, 마지막 데이터가 c와 같지 않다면
            elif not stack or stack.pop() != c: 
                return False
        return not stack # 스택이 비었다 : True


a = Solution()
print(a.isValid(")("))
print(a.isValid("(()())"))