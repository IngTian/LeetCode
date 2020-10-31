package com.company.leetcode.hard;

import java.util.*;

public class Q909 {

    public static class Solution {

        private int[] dx = {1, 2, 3, 4, 5, 6};

        public int snakesAndLadders(int[][] board) {

            if (board == null)
                return 0;

            int N = board.length;
            int N_sqr = N * N;
            int res = 0;

            LinkedList<IdxNode> queue = new LinkedList<>();
            queue.add(new IdxNode(1, 0, false));
            while (!queue.isEmpty()) {

                IdxNode cur_node = queue.remove();
                int idx = cur_node.idx;
                int level = cur_node.level;
                boolean used_ladder = cur_node.used_ladder;

                int row = get_m(idx, N);
                int col = get_n(idx, N);

                if (idx == N_sqr) {
                    res = level;
                    break;
                }

                if (board[row][col] != -1 && used_ladder == false)
                    queue.add(new IdxNode(board[row][col], level + 1, true));

                if (used_ladder == false) {
                    for (int i = 0; i < dx.length && idx + dx[i] <= N_sqr; i++) {

                        int r = get_m(idx + dx[i], N);
                        int c = get_n(idx + dx[i], N);

                        if (board[r][c] != -1)
                            queue.add(new IdxNode(board[r][c], level + 1, true));
                    }
                }

                int frontest_idx = get_frontest_idx(idx, N_sqr, board, N);
                if (frontest_idx != -1)
                    queue.add(new IdxNode(frontest_idx, level + 1, used_ladder));

            }

            return res;
        }

        private class IdxNode {
            int idx;
            int level;
            boolean used_ladder;

            private IdxNode(int idx, int level, boolean used_ladder) {
                this.idx = idx;
                this.level = level;
                this.used_ladder = used_ladder;
            }

            ;
        }

        private int get_frontest_idx(int cur_idx, int N_sqr, int[][] board, int N) {

            for (int i = cur_idx + dx[5]; i > cur_idx; i--) {

                if (i >= N_sqr)
                    continue;

                int row = get_m(i, N);
                int col = get_n(i, N);
                if (board[row][col] != -1)
                    continue;
                else
                    return i;
            }

            return -1;
        }

        private int get_m(int idx, int N) {
            int plane_row = (idx - 1) / N + 1;
            return N - plane_row;
        }

        private int get_n(int idx, int N) {
            int plane_row = (idx - 1) / N + 1;
            int dx = idx - (plane_row - 1) * N;
            return plane_row % 2 == 0 ? N - dx : dx - 1;
        }
    }
}
