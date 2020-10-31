package com.company.leetcode.medium;

import java.util.*;

public class Q1091 {
    class Solution {
        public int shortestPathBinaryMatrix(int[][] grid) {
            pre_processing(grid);
            LinkedList<int[]> queue = new LinkedList<>();
            int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0}, dc = {-1, 0, 1, 1, 1, 0, -1, -1};
            queue.add(new int[]{0, 0});
            while (!queue.isEmpty()) {
                int[] cor = queue.remove();
                int row = cor[0], col = cor[1], cur_dist = grid[row][col];

                for (int i = 0; i < 8; i++)
                    if (inMap(row + dr[i], col + dc[i], grid) && grid[row + dr[i]][col + dc[i]] == 0) {
                        int nex_row = row + dr[i], nex_col = col + dc[i];
                        if (nex_row == grid.length - 1 && nex_col == grid[0].length - 1) return cur_dist + 1;
                        grid[nex_row][nex_col] = cur_dist + 1;
                        queue.add(new int[]{nex_row, nex_col});
                    }
            }
            return 0;
        }

        private void pre_processing(int[][] grid) {
            for (int i = 0; i < grid.length; i++)
                for (int j = 0; j < grid[0].length; j++)
                    if (grid[i][j] == 1) grid[i][j] = Integer.MAX_VALUE;
            grid[0][0] = 1;
        }

        private boolean inMap(int r, int c, int[][] grid) {
            int height = grid.length, width = grid[0].length;
            return r >= 0 && r < height && c >= 0 && c < width;
        }
    }
}
