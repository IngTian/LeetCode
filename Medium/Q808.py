class Solution:

    def soupServings(self, N: int) -> float:

        if N >= 4800:
            return 1

        dp, Q, R = {}, divmod(N, 25)
        N = Q + (R > 0)

        def recursion(x, y):
            ans = 0.0
            if (x, y) not in dp:
                if x <= 0 or y <= 0:
                    ans = 0.5 if x <= 0 and y <= 0 else 1.0 if x <= 0 else 0
                else:
                    ans = 1 / 4 * (recursion(x - 4, y) + recursion(x - 3, y - 1) + recursion(x - 2, y - 2) + recursion(
                        x - 1, y - 3))
                dp[x, y] = ans
            return ans

        return recursion(N, N)
