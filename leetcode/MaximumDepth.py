# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
from collections import deque

class Solution:
    def maxDepth(self, root):
        visited = []
        max_Depth = 0
        if root == None:
            return max_Depth
        q = deque()
        q.append((root, 1))

        while q:
            cur_Node, cur_Depth = q.popleft()
            visited.append(cur_Node)
            if cur_Node.left:
                q.append((cur_Node.left, cur_Depth + 1))
            if cur_Node.right:
                q.append((cur_Node.right, cur_Depth + 1))
            max_Depth = max(max_Depth, cur_Depth)
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
