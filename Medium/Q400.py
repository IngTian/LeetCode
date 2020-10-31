class Solution(object):

    @staticmethod
    def findNthDigit(n: int) -> int:
        Nb, Mb, k, m = 0, 0, 1, 0
        while (True):
            Nb1 = Nb + k * 9 * pow(10, k - 1)
            if n > Nb1:
                Nb, Mb, k = Nb1, Mb + 9 * pow(10, k - 1), k + 1
            else:
                break

        NmNb = n - Nb
        idx = NmNb % k
        if idx == 0:
            m = NmNb // k + Mb
            return str(m)[len(str(m)) - 1]
        else:
            m = (NmNb - idx) // k + Mb + 1
            return str(m)[idx - 1]

        return 0
