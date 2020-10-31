from typing import List


class Solution:
    def minimumTotal(self, triangle: List[List[int]]) -> int:


        lev_min_dist = [0 for i in range(len(triangle))]


        