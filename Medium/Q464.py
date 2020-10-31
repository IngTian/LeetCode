class Solution:
    DICT = dict()

    def canIWin(self, maxChoosableInteger: int, desiredTotal: int) -> bool:
        """Determines whether a ex_player can win"""
        if maxChoosableInteger >= desiredTotal:
            return True
        elif (maxChoosableInteger ** 2 + maxChoosableInteger) // 2 < desiredTotal:
            return False

        M, D = "".join(["0" for n in range(maxChoosableInteger + 1)]), desiredTotal
        return Solution.recursion(M, D)

    @staticmethod
    def recursion(S: str, D: int) -> bool:
        """Solves the problem via recursion"""
        M, ans = len(S), False
        for i in range(1, M):
            if S[i] == '0':
                # This number is not selected
                trial_comb = setCharAt(S, '1', i)
                if i >= D \
                        or Solution.DICT.get(trial_comb) == False \
                        or not Solution.recursion(trial_comb, D - i):
                    # if we can absolutely win
                    Solution.DICT.update({S: True})
                    return True

        Solution.DICT.update({S: False})
        return False


def setCharAt(string: str, char: str, idx: int):
    """Helps set the indexed character to a specified value"""

    n, ans = len(string), ""

    if idx == n - 1:
        ans = string[:idx] + char
    elif idx == 0:
        ans = char + string[1:]
    elif 0 < idx < n - 1:
        ans = string[:idx] + char + string[idx + 1:]
    else:
        raise IndexError("idx is not within the valid range [0, {}]".format(n - 1))

    return ans


if __name__ == "__main__":
    sol = Solution()
    print(sol.canIWin(10, 35))
