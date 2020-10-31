from typing import List


class Solution:
    memo = []

    def longestIncreasingPath(self, matrix: List[List[int]]) -> int:
        h, w, ans = len(matrix), len(matrix[0]) if len(matrix) != 0 else 0, 0
        if not h or not w:
            return 0
        self.memo = [[0 for c in range(w)] for r in range(h)]
        for r in range(h):
            for c in range(w):
                if self.memo[r][c] == 0:
                    self.dfs(self.memo, matrix, r, c)
                ans = max(ans, self.memo[r][c])

        return ans

    def dfs(self, memo: List[List[int]], matrix: List[List[int]], r, c) -> int:

        def inMap(r: int, c: int, h: int, w: int) -> bool:
            return r >= 0 and r < h and c >= 0 and c < w

        memo[r][c] += 1
        h, w, dr, dc, ans, val = len(matrix), len(matrix[0]), [-1, 0, 1, 0], [0, 1, 0, -1], memo[r][c], matrix[r][c]
        for i in range(4):
            d_r, d_c = dr[i], dc[i]
            t_r, t_c = r + d_r, c + d_c
            if inMap(t_r, t_c, h, w) and matrix[t_r][t_c] > val:
                if memo[t_r][t_c] != 0:
                    ans = max(memo[t_r][t_c] + 1, ans)
                else:
                    ans = max(self.dfs(memo, matrix, t_r, t_c) + 1, ans)
        memo[r][c] = ans
        return ans


if __name__ == '__main__':
    data = [[3, 4, 5], [3, 2, 6], [2, 2, 1]]
    sol = Solution()
    sol.longestIncreasingPath(data)
