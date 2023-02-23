from collections import deque

class Solution:
    def maxDepth(self, root):
        max_Depth = 0
        if root == None:
            return max_Depth
        left_Depth = self.maxDepth(root.left)
        right_Depth = self.maxDepth(root.right)
        max_Depth = max(left_Depth, right_Depth) + 1
        return max_Depth


# tree = [3, 9, 20, None, None, 15, 7]
class TreeNode:
    def __init__(self, val, left = None, right = None):
        self.val = val
        self.left = left
        self.right = right

root = TreeNode(3)
root.left = TreeNode(9)
root.right = TreeNode(20)
root.right.left = TreeNode(15)
root.right.right = TreeNode(7)

test = Solution()
print(test.maxDepth(root))
