package com.company.leetcode.easy;

import java.util.*;

public class Q914 {

    public static class Solution {
        public boolean hasGroupsSizeX(int[] deck) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < deck.length; i++)
                if (!map.containsKey(deck[i])) map.put(deck[i], 1);
                else map.put(deck[i], map.get(deck[i]) + 1);

            ArrayList<Integer> frequency = new ArrayList<>(map.values());
            if (frequency.size() == 1)
                if (deck.length > 1) return true;
                else return false;

            int first_fre = frequency.get(0), sec_fre = frequency.get(1), gcd = gcd_euclid(first_fre, sec_fre);
            if (gcd == -1 || gcd < 2) return false;
            for (int i = 2; i < frequency.size(); i++) {
                gcd = gcd_euclid(gcd, frequency.get(i));
                if (gcd == -1 || gcd < 2) return false;
            }
            return true;

        }

        private int gcd_euclid(int a, int b) {
            int larger = Math.max(a, b), smaller = Math.min(a, b);
            if (smaller == 0) return larger;
            if (larger % smaller == 0) return smaller;
            return gcd_euclid(larger % smaller, smaller);
        }
    }

}
