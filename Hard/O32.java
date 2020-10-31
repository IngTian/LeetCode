package com.company.leetcode.hard;

import java.util.*;

public class O32 {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int x) {
            val = x;
        }
    }


    public static class Solution {

        Map<TreeNode, Integer> map = new HashMap<>();

        public List<List<Integer>> levelOrder(TreeNode root) {

            if (root == null)
                return null;

            map.put(root, 0);

            register_level(root);

            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);

            List<List<Integer>> res = new LinkedList<>();
            List<Integer> cur = new ArrayList<>();
            int level = 0;

            while (!queue.isEmpty()) {

                TreeNode cur_node = queue.remove();
                int cur_level = map.get(cur_node);
                if (cur_level > level) {
                    if (level % 2 != 0)
                        Collections.reverse(cur);

                    res.add(cur);
                    cur = new ArrayList<>();
                    cur.add(cur_node.val);
                    level = cur_level;
                } else
                    cur.add(cur_node.val);

                if (cur_node.left != null)
                    queue.add(cur_node.left);
                if (cur_node.right != null)
                    queue.add(cur_node.right);

            }

            return res;
        }

        private void register_level(TreeNode node) {
            int cur_level = map.get(node);

            if (node.left != null) {
                map.put(node.left, cur_level + 1);
                register_level(node.left);
            }

            if (node.right != null) {
                map.put(node.right, cur_level + 1);
                register_level(node.right);
            }
        }

    }


}
