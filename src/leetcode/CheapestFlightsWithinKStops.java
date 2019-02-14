package leetcode;

import java.util.*;

public class CheapestFlightsWithinKStops {
    int res = Integer.MAX_VALUE;
    int n;
    int dst;
    Map<String, Integer> map = new HashMap<>();

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        this.n = n;
        this.dst = dst;
        for (int[] e : flights) {
            map.put(e[0] + "_" + e[1], e[2]);
        }
        Set<Integer> set = new HashSet<>();
        set.add(src);
        dfs(src,   K, set, 0);

        if (res == Integer.MAX_VALUE) {
            res = -1;
        }
        return res;
    }

    private void dfs(int last, int stop, Set<Integer> set, int sum) {
        if (stop < 0)
            return;
        for (int i=0;  i<n; i++) {
            if (set.contains(i)) {
                continue;
            }
            Integer cost = map.get(last + "_" + i);
            if (cost != null && cost + sum < res) {
                if (i == dst ) {
                    res = cost + sum;
                } else {
                    set.add(i);
                    dfs(i,   stop-1, set, cost + sum);
                    set.remove(i);
                }
            }
        }
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops solution = new CheapestFlightsWithinKStops();
        int[][] edges = {{0,1,100},{1,2,100},{0,2,500}};
        int result = solution.findCheapestPrice(3, edges, 0, 2, 0);
        System.out.println("=========================================");
        System.out.println("result = " + result);

    }
}
