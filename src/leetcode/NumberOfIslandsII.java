package leetcode;

import java.util.*;

public class NumberOfIslandsII {

    StringBuilder sb = new StringBuilder();
    private String getKey(int x, int y) {
        sb.setLength(0);
        return  sb.append(x).append('-').append(y).toString();
    }

    private List<String> getDirects(int[] a, int m, int n ) {
        List<String> list = new ArrayList<>();
        if (a[0] + 1 < m) {
            list.add(getKey(a[0] + 1, a[1]));
        }
        if (a[0] - 1 >=0) {
            list.add(getKey(a[0] - 1, a[1]));
        }
        if (a[1] + 1 < n) {
            list.add(getKey(a[0], a[1] + 1));
        }
        if (a[1] - 1 >= 0) {
            list.add(getKey(a[0], a[1]- 1));
        }
        return list;
    }

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        List<Integer> res = new ArrayList<>();
        Map<String, Set<String>> map = new HashMap<>();
        int cnt = 0;
        for(int[] op: positions) {
            String key = getKey(op[0], op[1]);
            List<String> dirs =  getDirects(op, m, n);
            List<String> link = new ArrayList<>();
            for(String dir: dirs) {
                if (map.containsKey(dir)) {
                    link.add(dir);
                }
            }
            if (link.size() == 0) {
                Set<String> set = new HashSet<>();
                set.add(key);
                map.put(key, set);
                cnt++;
            } else {
                Set<String> set1 = map.get(link.get(0));
                set1.add(key);
                map.put(key, set1);
                for(int i=1; i<link.size(); i++) {
                    Set<String> set2 = map.get(link.get(i));
                    if (set1 != set2) {
                        Iterator<String> it = set2.iterator();
                        while (it.hasNext()) {
                            String key2 = it.next() ;
                            map.put(key2, set1);
                        }
                        set1.addAll(set2);
                        cnt--;
                    }
                }
            } // else
            res.add(cnt);
        } // for
        return res;
    }

    public static void main(String[] args) {
        int[][] positions = {

                {0,0}, {0,1}, {1,2},{2,1}, {1,1}
        };

        NumberOfIslandsII solution = new NumberOfIslandsII();
        System.out.println(solution.numIslands2(3,3, positions));

    }


}
