from math import sqrt
from typing import List


class Solution:

    def validSquare(self, p1: List[int], p2: List[int], p3: List[int], p4: List[int]) -> bool:
        AB, AC, BC = self.getDist()

    def getDist(p1: List[int], p2: List[int]) -> float:
        return sqrt((p1[0] - p2[0]) ** 2 + (p1[1] - p2[1]) ** 2)