from typing import List


class Solution:
    def maxSatisfaction(self, satisfaction: List[int]) -> int:
        Sum, n, ans = [0 for _ in range(len(satisfaction) + 1)], len(satisfaction), 0
        satisfaction.sort()
        for index in range(n):
            pre_sum, val = Sum[index], satisfaction[index]
            increment = pre_sum + val
            if increment < 0:
                break
            else:
                Sum[index + 1] = pre_sum + val
                ans += increment
        return ans