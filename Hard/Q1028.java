package com.company.leetcode.hard;


import java.util.*;

public class Q1028 {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static class Solution {

        ArrayList<Integer> arr = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        Integer index = 0;

        public TreeNode recoverFromPreorder(String S) {

            if (S == null || S.equals(" "))
                return null;

            process_and_record(S);
            return recursion();
        }

        private void process_and_record(String s) {
            int cur_level = 0;
            StringBuffer buf = new StringBuffer();
            int cur_val = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) >= '0' && s.charAt(i) <= '9')
                    buf.append(s.charAt(i));
                else {
                    if (i == 0 || s.charAt(i - 1) != '-') {
                        cur_val = Integer.parseInt(buf.toString());
                        this.map.put(cur_val, cur_level);
                        this.arr.add(cur_val);
                        cur_level = 1;
                        buf = new StringBuffer();
                    } else
                        cur_level += 1;
                }
            }

            cur_val = Integer.parseInt(buf.toString());
            this.map.put(cur_val, cur_level);
            this.arr.add(cur_val);
            cur_level = 1;
        }

        private TreeNode recursion() {
            if (this.index >= arr.size())
                return null;
            else if (this.index == arr.size() - 1) {
                return new TreeNode(arr.get(index++));
            } else {
                int cur_val = arr.get(index);
                int cur_level = map.get(cur_val);

                index += 1;

                int next_val = arr.get(index);
                int next_level = map.get(next_val);

                if (next_level > cur_level) {
                    TreeNode cur_node = new TreeNode(cur_val);
                    cur_node.left = recursion();

                    if (index < arr.size()) {
                        cur_node.right = cur_level + 1 == map.get(arr.get(index)) ? recursion() : null;
                        return cur_node;
                    } else
                        return cur_node;
                } else
                    return new TreeNode(cur_val);
            }
        }
    }
}
