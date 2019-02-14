package leetcode;

import java.util.*;

public class CheapestFlightsWithinKStops3 {

    class Tuple {
        int city;
        int price;
        int stops;

        public Tuple(int c, int p, int s) {
            city = c;
            price = p;
            stops = s;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(i, new HashMap<>());
        }

        for (int[] flight : flights) {
            map.get(flight[0]).put(flight[1], flight[2]);
        }

        PriorityQueue<Tuple> queue
                = new PriorityQueue<>((a, b) -> a.price - b.price);
        queue.offer(new Tuple(src, 0, -1));

        while (!queue.isEmpty()) {
            Tuple curr = queue.poll();

            if (curr.city == dst) {
                return curr.price;
            }

            if (curr.stops == K) {
                continue;
            }

            for (Map.Entry<Integer, Integer> next : map.get(curr.city).entrySet()) {
                queue.offer(new Tuple(next.getKey()
                        , curr.price + next.getValue(), curr.stops + 1));
            }
        }

        return -1;
    }


    public static void main(String[] args) {
        CheapestFlightsWithinKStops3 solution = new CheapestFlightsWithinKStops3();
        int[][] edges = {{0,1,100},{1,2,100},{0,2,500}};
        int result = solution.findCheapestPrice(3, edges, 0, 2, 1);
        System.out.println("==================== 3 =====================");
        System.out.println("result = " + result);

    }
}
