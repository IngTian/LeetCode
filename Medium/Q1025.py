class Solution:

    def divisorGame(self, N: int) -> bool:
        global dp
        if N in dp:
            return dp[N]
        else:
            dp.update({N: False})
            for choice in range(1, N):
                if N % choice == 0 and not self.divisorGame(N - choice):
                    dp[N] = True
            return dp[N]


dp = {
    1: False,
    2: True,
    3: False
}

if __name__ == "__main__":
    sol = Solution()
    sol.divisorGame(1)