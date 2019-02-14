package leetcode;

import java.util.*;

public class TopKFrequentElements {

    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length ==0  || k==0)
            return res;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i: nums) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i)+1);
            } else {
                map.put(i,1);
            }
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>(k, (a, b) -> b.getValue() - a.getValue());
        for (Map.Entry entry: map.entrySet()) {
            queue.offer(entry);
        }

        while (!queue.isEmpty()) {
            Map.Entry<Integer, Integer> entry = queue.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}
