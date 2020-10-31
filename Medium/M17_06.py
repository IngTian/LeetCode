class Solution:

    def numberOf2sInRange(self, n: int) -> int:

        f_lst, str_num, ans = [0], str(n), 0
        m = len(str_num)

        def f(n):
            if n < len(f_lst):
                return f_lst[n]
            else:
                for i in range(len(f_lst), n + 1):
                    f_lst.append(10 * f_lst[i - 1] + 10 ** (i - 1))
                return f_lst[n]

        for k in reversed(range(1, m + 1)):
            a_k = int(str_num[m - k])
            if a_k > 2:
                ans += a_k * f(k - 1) + 10 ** (k - 1)
            elif a_k < 2:
                ans += a_k * f(k - 1)
            else:
                ans += 2 * f(k - 1) + (n % (10 ** (k - 1))) + 1

        return ans