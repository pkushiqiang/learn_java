package leetcode;

import java.util.*;

public class BusRoutes {
    public static void main(String[] args) {
        BusRoutes solution = new BusRoutes();
        //  int[][] edges = {{1,2,7},{3,6,7},{4,6,8},{1,5,6}};
        int[][] edges = {{0,1,2,3,4,5},{5,6,7,8,9,10},{10,11,12,13,14,15},{15,16,17,18,19,20},{20,21,22,23,24,25}};
        int result = solution.numBusesToDestination(edges, 0, 25);
        System.out.println("=========================================");
        System.out.println("result = " + result);
    }

    public int numBusesToDestination(int[][] routes, int S, int T) {
        if (S==T)
            return 0;
        List<Set<Integer>> stopSets = new ArrayList<>();

        for (int i = 0; i < routes.length; i++) {
            int[] r = routes[i];
            Set<Integer> set = new HashSet<Integer>();
            for (int s : r) {
                set.add(s);
            }
            stopSets.add(set);
        }

        List<Set<Integer>> conSets = new ArrayList<>();
        for (int i = 0; i < routes.length; i++) {
            int[] r = routes[i];
            Set<Integer> set = new HashSet<Integer>();
            for (int j = 0; j < routes.length; j++) {
                if (i == j) continue;
                for (int s : r) {
                    if (stopSets.get(j).contains(s)) {
                        set.add(j);
                    }
                }
            }
            conSets.add(set);
        }

        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Set<Integer> tarSet = new HashSet<>();
        boolean[] v = new boolean[routes.length];
        for (int i = 0; i < routes.length; i++) {
            if (stopSets.get(i).contains(S)) {
                queue1.offer(i);
            }

            if (stopSets.get(i).contains(T)) {
                tarSet.add(i);
            }
        }

        if (tarSet.size() == 0) {
            return -1;
        }

        for (int i : queue1) {
            if (tarSet.contains(i))
                return 1;
            v[i] = true;
        }
        int res = 1;

        while (!queue1.isEmpty()) {
            res++;
            while (!queue1.isEmpty()) {
                int src = queue1.poll();
                Iterator<Integer> iterator = conSets.get(src).iterator();
                while (iterator.hasNext()) {
                    int t = iterator.next();
                    if (v[t]) continue;
                    if (tarSet.contains(t)) {
                        return res;
                    } else {
                        v[t] = true;
                        queue2.offer(t);
                    }

                }
            }
            Queue<Integer> t;
            t = queue1;
            queue1 = queue2;
            queue2 =t;
        }
        return -1;
    }
}
