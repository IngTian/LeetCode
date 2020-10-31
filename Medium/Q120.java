package com.company.leetcode.medium;

import com.sun.org.apache.xerces.internal.dom.CoreDocumentImpl;
import com.sun.org.apache.xpath.internal.operations.String;

import java.util.*;

public class Q120 {

    public static class Solution {

        int[][] min_dist;

        public int minimumTotal(List<List<Integer>> triangle) {

            if(triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0) return 0;

            int height = triangle.size(), width = triangle.get(height - 1).size(), ans = 0;

            boolean[][] visited = new boolean[height][width];
            visited[0][0] = true;
            min_dist = new int[height][width];
            for(int i = 0; i < min_dist.length; i++)for (int j = 0; j < triangle.get(i).size(); j++)
                min_dist[i][j] = Integer.MAX_VALUE;
            min_dist[0][0] = triangle.get(0).get(0);

            ArrayList<Cordinate> queue = new ArrayList<>();
            for(int i = 0; i < height; i++)for(int j = 0; j < triangle.get(i).size(); j++) queue.add(new Cordinate(i, j));
            while(!queue.isEmpty()){
                Cordinate cor = queue.remove(0);
                int r = cor.r, c = cor.c, nex_r = r + 1, nex_c1 = c, nex_c2 = c + 1, cur_dist = min_dist[r][c];
                visited[r][c] = true;
                if(nex_r < height){

                    if(!visited[nex_r][nex_c1])
                        min_dist[nex_r][nex_c1] = Math.min(cur_dist + triangle.get(nex_r).get(nex_c1), min_dist[nex_r][nex_c1]);
                    if(!visited[nex_r][nex_c2])
                        min_dist[nex_r][nex_c2] = Math.min(cur_dist + triangle.get(nex_r).get(nex_c2), min_dist[nex_r][nex_c2]);
                }
                else if(nex_r == height)
                    ans = cur_dist < ans ? cur_dist : ans;

                queue.sort(new DistanceComparator());
            }

            StringBuffer buffer = new StringBuffer();
            for(int i = 0; i < min_dist.length; i++){
                for(int j = 0; j < min_dist[i].length; j++)
                    buffer.append(min_dist[i][j] + " ");
                buffer.append("\n");
            }
            System.out.println(buffer.toString());

            return ans;
        }

        private class DistanceComparator implements Comparator<Cordinate>{
            public int compare(Cordinate c1, Cordinate c2){
                int dist_c1 = min_dist[c1.r][c1.c], dist_c2 = min_dist[c2.r][c2.c];
                if(dist_c1 < dist_c2)
                    return -1;
                else if(dist_c1 > dist_c2)
                    return 1;
                else
                    return 0;
            }
        }

        private class Cordinate {
            private int r, c;

            private Cordinate(int r, int c) {
                this.r = r;
                this.c = c;
            }
        }

        public int minimumTotal2(List<List<Integer>> triangle) {
            int n = triangle.size();
            int[][] f = new int[n][n];
            f[0][0] = triangle.get(0).get(0);
            for (int i = 1; i < n; ++i) {
                f[i][0] = f[i - 1][0] + triangle.get(i).get(0);
                for (int j = 1; j < i; ++j) {
                    f[i][j] = Math.min(f[i - 1][j - 1], f[i - 1][j]) + triangle.get(i).get(j);
                }
                f[i][i] = f[i - 1][i - 1] + triangle.get(i).get(i);
            }
            int minTotal = f[n - 1][0];
            for (int i = 1; i < n; ++i) {
                minTotal = Math.min(minTotal, f[n - 1][i]);
            }
            StringBuffer buffer = new StringBuffer();
            for(int i = 0; i < f.length; i++){
                for(int j = 0; j < f[i].length; j++){
                    buffer.append(f[i][j] + " ");
                }
                buffer.append("\n");
            }
            System.out.println(buffer.toString());
            return minTotal;
        }
    }

}
