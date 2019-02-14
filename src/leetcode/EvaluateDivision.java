package leetcode;

import java.util.*;

public class EvaluateDivision {
    private Map<String,Map<String,Double>> graphMap = new HashMap<>();
    private Map<String,Set<String>> setMap = new HashMap<>();

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        for (int i=0; i<equations.length; i++) {
            String[] pair = equations[i];
            addPair(pair[0], pair[1], values[i]);
            addPair(pair[1], pair[0], 1.0/values[i]);

            setupSet(pair);
        }
        double[] res = new double[queries.length];
        for (int i=0; i<queries.length; i++) {
            String a = queries[i][0];
            String b = queries[i][1];
            if (setMap.get(a) == null || setMap.get(b) == null
                    || setMap.get(a)  != setMap.get(b) ) {
                res[i] = -1;
            } else {
                Set<String> set = new HashSet<>();
                res[i] = search(set, a, b);
            }

        }
        return res;
    }

    private double search(Set<String> set, String a, String b) {
        set.add(a);
        Map<String,Double> map = graphMap.get(a);
        if (map.get(b)!=null) {
            return map.get(b);
        } else {
            for (String k : map.keySet()) {
                if(!set.contains(k)) {
                    double v =  search(set,k,b);
                    if (v!=-1) {
                        return v * map.get(k);
                    }
                }
            }
            return -1;
        }
    }

    private void setupSet(String[] pair) {
        if ( (setMap.get(pair[0]) != null) && (setMap.get(pair[1]) != null) ) {
            setMap.get(pair[0]).addAll(setMap.get(pair[1]));
            for (Iterator<String> it = setMap.get(pair[1]).iterator(); it.hasNext();) {
                setMap.put(it.next(), setMap.get(pair[0]));
            }
        } else if (setMap.get(pair[0]) != null) {
            setMap.get(pair[0]).add(pair[1]);
            setMap.put(pair[1], setMap.get(pair[0]));
        } else if   (setMap.get(pair[1]) != null) {
            setMap.get(pair[1]).add(pair[0]);
            setMap.put(pair[0], setMap.get(pair[1]));
        } else {
            Set<String> set = new HashSet<>();
            set.add(pair[0]);
            set.add(pair[1]);
            setMap.put(pair[0],set);
            setMap.put(pair[1],set);
        }
    }

    private void addPair(String a, String b, double v) {
        Map<String,Double> map = graphMap.get(a);
        if (map == null) {
            map = new HashMap<>();
            graphMap.put(a, map);
            map.put(a, 1.0);
        }
        map.put(b,v);
    }

    public static void main(String[] args){
        EvaluateDivision solution = new EvaluateDivision();
        String[][] equations = {{"a","b"},{"e","f"},{"b","e"}};
        double[] values = {3.4,1.4,2.3};
        String[][] queries = {{"b","a"},{"a","f"},{"f","f"},{"e","e"},{"c","c"},{"a","c"},{"f","e"}};

        double[] res = solution.calcEquation(equations, values, queries);
        for (double d : res) {
            System.out.print(d + ", ");
        }
        System.out.println();
    }
}
