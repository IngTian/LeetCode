package com.company.leetcode.medium;

import java.util.*;

public class Q934 {

    public static class Solution {

        private int[] four_dir_dr = {0, 1, 0, -1};
        private int[] four_dir_dc = {1, 0, -1, 0};

        public int shortestBridge(int[][] A) {

            LinkedList<Cordinate> queue = new LinkedList<>();

            boolean fir_land_encontered = false;
            for(int i = 0; i < A.length; i++){
                for(int j = 0; j < A[0].length; j++)
                    if(A[i][j] == 1){dfs(queue, A, new Cordinate(i, j)); fir_land_encontered = true; break;}
                if(fir_land_encontered) break;
            }

            while(!queue.isEmpty()){
                Cordinate cur = queue.remove();
                int r = cur.row, c = cur.col, d = cur.dist;

                for(int i = 0; i < 4; i++){
                    int dr = four_dir_dr[i], dc = four_dir_dc[i];
                    if(inMap(r + dr, c + dc, A.length, A[0].length) && A[r + dr][c + dc] != -1)
                    {
                        queue.add(new Cordinate(r + dr, c + dc, d + 1));
                        if(A[r + dr][c + dc] == 1) return d;
                        A[r + dr][c + dc] = -1;
                    }
                }

            }
            return 1;
        }

        private void dfs(List<Cordinate>queue, int[][]graph, Cordinate point){
            int r = point.row, c = point.col;
            graph[r][c] = -1;

            boolean isCoast = false;
            for(int i = 0; i < 4; i++){
                int dr = four_dir_dr[i], dc = four_dir_dc[i];
                if(inMap(r + dr, c + dc, graph.length, graph[0].length) && graph[r + dr][c + dc] == 0)  isCoast = true;
            }
            if(isCoast) queue.add(point);

            for(int i = 0; i < 4; i++){
                int dr = four_dir_dr[i], dc = four_dir_dc[i];
                if(inMap(r + dr, c + dc, graph.length, graph[0].length) && graph[r + dr][c + dc] == 1)  dfs(queue, graph, new Cordinate(r + dr, c + dc));
            }

        }

        private boolean inMap(int row, int col, int height, int width){
            return
                    row >= 0 &&
                            row < height &&
                            col >= 0 &&
                            col < width;
        }

        private class Cordinate{
            int row;
            int col;
            int dist;
            private Cordinate(int r, int c){row = r; col = c; dist = 0;}
            private Cordinate(int r, int c, int d){row = r; col = c; dist = d;}
        }

    }
}
