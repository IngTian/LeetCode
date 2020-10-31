package com.company.leetcode.hard;

import java.util.*;

public class Q207 {
    public static class Solution {

        public boolean canFinish(int numCourses, int[][] prerequisites) {

            int[] parr_num = new int[numCourses]; //Array to store the number of parrent nodes of a particular node
            List<List<Integer>> adjacency_matrix = new ArrayList<>(); //List to store the connection information of a particular node
            for (int i = 0; i < numCourses; i++)
                adjacency_matrix.add(new ArrayList<>());

            for (int i = 0; i < prerequisites.length; i++) {
                int[] cur_pair = prerequisites[i];
                int parr = cur_pair[1];
                int child = cur_pair[0];
                parr_num[child]++;
                adjacency_matrix.get(parr).add(child);
            }

            LinkedList<Integer> queue = new LinkedList<>();
        /*
        First add all vertixes, by vertixes we mean nodes that are without any parrent node
        */
            for (int i = 0; i < parr_num.length; i++)
                if (parr_num[i] == 0)
                    queue.add(i);

        /*
        Start BFS
        */
            while (!queue.isEmpty()) {
                int course = queue.remove();
                numCourses--;
                List<Integer> children = adjacency_matrix.get(course);
                for (int cur : children)
                    if ((--parr_num[cur]) == 0)
                        queue.add(cur);
            }

            return numCourses == 0;
        }
    }
}
