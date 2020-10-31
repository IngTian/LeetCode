from typing import List


class Solution:
    class TreeNode:
        def __init__(self, x):
            self.val = x
            self.left = None
            self.right = None

    def rob(self, root: TreeNode) -> int:

        if root == None:
            return 0
        if root.left == None and root.right == None:
            return root.val

        l_nodes, r_nodes, l_dp, r_dp, ans, root_val = [], [], [], [], 0, root.val

        node = root
        while node.left != None:
            node = node.left
            l_nodes.append(node.val)

        node = root
        while node.right != None:
            node = node.right
            r_nodes.append(node.val)

        l_dp = max_robbery(l_nodes)
        r_dp = max_robbery(r_nodes)

        if l_dp == None:
            if len(r_dp) >= 2:
                ans = max(r_dp[len(r_dp) - 1], r_dp[len(r_dp) - 2] + root_val)
            else:
                ans = max(root_val, root.right.val)
        if r_dp == None:
            if len(l_dp) >= 2:
                ans = max(l_dp[len(l_dp) - 1], l_dp[len(l_dp) - 2] + root_val)
            else:
                ans = max(root_val, root.left.val)
        else:
            if len(l_dp) == 1 and len(r_dp) == 1:
                ans = max(root_val, root.left.val, root.right.val)
            else:
                ans = max(root_val + l_dp[len(l_dp) - 2] + r_dp[len(r_dp) - 2],
                          l_dp[len(l_dp) - 1] + r_dp[len(r_dp) - 1])

        return ans


def max_robbery(nums: List[int]) -> List[int]:
    n, dp = len(nums), []

    if n >= 2:
        dp.append(nums[n - 1])
        dp.append(max(nums[n - 2], nums[n - 1]))
        for i in reversed(range(n - 2)):
            dp.append(max(dp[i - 1], dp[i - 2] + nums[i]))
    elif n == 1:
        dp.append(nums[n - 1])
    elif n == 0:
        dp = None
    return dp
