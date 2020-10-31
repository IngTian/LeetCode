from typing import List
from collections import OrderedDict


class Solution:
    def deleteAndEarn(self, nums: List[int]) -> int:

        if nums is None or len(nums) == 0:
            return 0
        elif len(nums) == 1:
            return nums[0]

        max_val = 0
        for number in nums:
            max_val = max(max_val, number)
        fre, dp = [0] * (max_val + 1), [0] * (max_val + 1)
        for number in nums:
            fre[number] += 1
        dp[1] = fre[1] * 1
        for i in range(2, max_val + 1):
            dp[i] = max(dp[i - 1], dp[i - 2] + fre[i] * i)

        return dp[max_val]