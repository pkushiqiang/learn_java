package airbnb;

import java.util.*;



public class MinimumVerticestoTraverseDirectedGraph {
    /*
        Minimum Vertices to Traverse Directed Graph - DFS
        https://cs.stackexchange.com/questions/1698/find-the-minimal-number-of-runs-to-visit-every-edge-of-a-directed-graph
        AirBnB Interview Question
     */
    public class Solution {
        private void search(Set<Integer> res, Map<Integer, Set<Integer>> nodes, int cur, int start,
                            Set<Integer> visited, Set<Integer> currVisited) {
            currVisited.add(cur);
            visited.add(cur);
            for (int next : nodes.get(cur)) {
                if (res.contains(next) && next != start) {
                    res.remove(next);
                }
                if (!currVisited.contains(next)) {
                    search(res, nodes, next, start, visited, currVisited);
                }
            }
        }

        public List<Integer> getMin(int[][] edges, int n) {
            Map<Integer, Set<Integer>> nodes = new HashMap<>();
            for (int i = 0; i < n; i++) {
                nodes.put(i, new HashSet<>());
            }
            for (int[] edge : edges) {
                nodes.get(edge[0]).add(edge[1]);
            }

            Set<Integer> visited = new HashSet<>();
            Set<Integer> res = new HashSet<>();
            for (int i = 0; i < n; i++) {
                if (!visited.contains(i)) {
                    res.add(i);
                    visited.add(i);
                    search(res, nodes, i, i, visited, new HashSet<>());
                }
            }

            return new ArrayList<>(res);
        }
    }

    public static void main(String[] args) {
      //  int[][] edges = {{0, 0}, {1, 2}, {2, 0}, {2, 3}, {3, 1}};
        int[][] edges = {{0, 1}, {1, 0}, {2, 1}, {3, 1}, {3, 2}};
        Solution solution = new MinimumVerticestoTraverseDirectedGraph().new Solution();
        List<Integer> res = solution.getMin(edges, 4);
        System.out.println(res);
    }

}