import sys
from typing import List


class Solution:
    def coinChange(self, coins: List[int], amount: int) -> int:
        if amount < 1:
            return -1
        dp = []
        for i in range(0, amount + 1):
            if i in coins:
                dp.append(1)
            else:
                dp.append(0)

        for j in range(1, amount + 1):
            min_val = sys.maxsize
            for i in coins:
                if j - i < 1 or dp[j - i] == 0:
                    pass
                else:
                    min_val = min(min_val, dp[j - i] + 1)
            dp[j] = min_val if min_val != sys.maxsize else dp[j]

        return -1 if dp[amount] == 0 else dp[amount]


if __name__ == "__main__":
    coins, amount = [1,2,5], 11
    sol = Solution()
    sol.coinChange(coins, amount)