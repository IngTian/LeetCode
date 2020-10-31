package com.company.leetcode.medium;

import java.util.*;

public class Q743 {

    public static class Solution {

        private Map<Integer, ArrayList<HalfVector>> map;
        private boolean[] visited;

        public int networkDelayTime(int[][] times, int N, int K) {
            visited = new boolean[N + 1];
            map = new HashMap<>();
            for (int i = 1; i <= N; i++) {
                ArrayList<HalfVector> arr = new ArrayList<>();
                map.put(i, arr);
            }
            for(int i = 0; i < times.length; i++){
                int[] vector = times[i];
                int start = vector[0];
                int end = vector[1];
                int delay = vector[2];
                map.get(start).add(new HalfVector(end, delay));
            }

            LinkedList<FrontNode> queue = new LinkedList<>();
            queue.add(new FrontNode(K, 0));
            visited[K] = true;
            int max_delay = 0;
            while (!queue.isEmpty()) {
                FrontNode node = queue.remove();
                int nd = node.node;
                int ttl_d = node.ttl_delay;
                N--;
                max_delay = ttl_d > max_delay ? ttl_d : max_delay;

                ArrayList<HalfVector> children_vectors = map.get(nd);
                for (HalfVector vec : children_vectors)
                    if (visited[vec.end_node] == false) {
                        queue.add(new FrontNode(vec.end_node, ttl_d + vec.delay));
                        visited[vec.end_node] = true;
                    }
            }

            return N == 0 ? max_delay : -1;
        }

        private class FrontNode {
            int node;
            int ttl_delay;

            private FrontNode(int node, int ttl_delay) {
                this.node = node;
                this.ttl_delay = ttl_delay;
            }
        }

        private class HalfVector {
            int end_node;
            int delay;

            private HalfVector(int end_node, int delay) {
                this.end_node = end_node;
                this.delay = delay;
            }
        }

    }
}
