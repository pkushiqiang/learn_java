package leetcode;

import java.util.*;

public class CheapestFlightsWithinKStops2 {

    int n;
    List<Map<String, Integer>> maps = new ArrayList<>();

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        this.n = n;

        Map<String, Integer> map0 = new HashMap<>();
        maps.add(map0);
        for (int[] e : flights) {
            map0.put(e[0] + "_" + e[1], e[2]);
        }

        Map<String, Integer> lastMap = map0;
        for (int s=0; s<K; s++) {
            Map<String, Integer> map = new HashMap<>();
            maps.add(map);
            for (String key : lastMap.keySet()) {
                addRoute(map0, map, key, lastMap.get(key));
            }
            lastMap = map;
        }

        String key = src + "_" + dst;
        for(int i = maps.size()-1; i>=0; i--) {
            if (maps.get(i).containsKey(key)) {
                return maps.get(i).get(key);
            }
        }
        return -1;
    }


    private void addRoute(Map<String, Integer> map0,  Map<String, Integer> map, String key, int sum) {
        String[] strs = key.split("_");
        int src = Integer.parseInt(strs[0]);
        int tar = Integer.parseInt(strs[1]);

        for (int i=0; i<n; i++) {
            String shortKey = tar + "_" + i;
            if (tar!=i && src!=i && map0.containsKey(shortKey)) {
                int cost = map0.get(shortKey) + sum;
                String longKey = src + "_" + i;
                if (isCheap(longKey, cost)) {
                    map.put(longKey, cost);
                }
            }
        }
    }

    private boolean isCheap(String key, int cost) {
        for(Map<String, Integer> map: maps) {
            if (map.containsKey(key) && map.get(key) < cost)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        CheapestFlightsWithinKStops2 solution = new CheapestFlightsWithinKStops2();
        int[][] edges = {{0,1,100},{1,2,100},{0,2,500}};
        int result = solution.findCheapestPrice(3, edges, 0, 2, 1);
        System.out.println("=========================================");
        System.out.println("result = " + result);

    }
}
