from typing import Set


class Solution:
    def waysToChange(self, n: int) -> int:
        mod, f, coins = 1000000007, [[0 for i in range(n + 1)] for _ in range(5)], [1, 5, 10, 25]
        f[0][0] = f[1][0] = f[2][0] = f[3][0] = f[4][0] = 1

        for v in range(1, n + 1):
            for i in range(1, 5):
                f[i][v] = f[i - 1][v] + (f[i][v - coins[i - 1]] if v - coins[i - 1] >= 0 else 0)

        return f[4][n]


if __name__ == "__main__":
    sol = Solution()
    sol.waysToChange()
