from typing import List


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    dp = {}

    def generateTrees(self, n: int) -> List[TreeNode]:
        return Solution.recursion(1, n)

    @staticmethod
    def recursion(start: int, end: int) -> List[TreeNode]:
        global dp
        if (start, end) not in dp:
            if start == end:
                dp[start, end] = [TreeNode(start)]
            else:
                arrg_lst = []
                for root_val in range(start, end + 1):
                    left_node_arrg = Solution.recursion(start, root_val - 1) \
                        if root_val - 1 >= start else [None]
                    right_node_arrg = Solution.recursion(root_val + 1, end) \
                        if root_val + 1 <= end else [None]
                    for i in range(len(left_node_arrg)):
                        for j in range(len(right_node_arrg)):
                            left_node, right_node, root = left_node_arrg[i], right_node_arrg[j], TreeNode(root_val)
                            root.left, root.right = left_node, right_node
                            arrg_lst.append(root)

                dp[start, end] = arrg_lst
        return dp[start, end]
