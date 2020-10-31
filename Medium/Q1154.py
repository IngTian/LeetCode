class Solution:

    def dayOfYear(self, date: str) -> int:

        year, month, day, res = int(date[:4]), int(date[5:7]), int(date[8:]), 0

        days_of_each_month = [
            [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31],
            [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
        ][isLeapYear(year)]

        for m in range(month - 1):
            res += days_of_each_month[m]
        res += day

        return res

def isLeapYear(year: int) -> bool:
    return year % 4 == 0 and (year % 100 != 0 or year % 400 == 0)

def main():
    sol = Solution()
    print(sol.dayOfYear("2019-01-09"))

if __name__ == '__main__':
    main()