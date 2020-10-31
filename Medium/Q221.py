from typing import List


class Solution:
    def maximalSquare(self, matrix: List[List[str]]) -> int:
        ans, h, w = 0, len(matrix), len(matrix[0]) if len(matrix) > 0 else 0
        if (h, w) == (0, 0):
            ans = 0
        else:
            for r in reversed(range(h)):
                for c in reversed(range(w)):
                    matrix[r][c] = int(matrix[r][c])
                    if matrix[r][c] != 0:
                        val, sub_val, r_val, br_val = matrix[r][c], matrix[r + 1][c] if r + 1 < h else 0, \
                                                      matrix[r][c + 1] if c + 1 < w else 0, matrix[r + 1][
                                                          c + 1] if r + 1 < h and c + 1 < w else 0
                        min_w = min(sub_val, r_val)
                        matrix[r][c] = min_w + 1 if br_val >= min_w else min_w
                        ans = max(ans, matrix[r][c] ** 2)
        return ans


test = [["1", "0", "1", "0", "0"], ["1", "0", "1", "1", "1"], ["1", "1", "1", "1", "1"], ["1", "0", "0", "1", "0"]]

if __name__ == '__main__':
    sol = Solution()
    sol.maximalSquare(test)
