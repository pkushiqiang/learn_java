package airbnb;


import java.util.HashMap;
import java.util.Map;

public class MinTrip2 {

    private static final int SCALE =10;

    public int findMinTrips(double[] costs, double  budget) {
        int intBudget = (int) (budget * SCALE);
        int[] intCosts = new int[costs.length];
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<costs.length; i++) {
            intCosts[i] = (int) (costs[i] * SCALE);
        }

        int result =search(costs, intCosts, intBudget, map);
        return result;
    }

    private int search(double[] costs, int[] intCosts, int intBudget, Map<Integer, Integer> map) {
        if (intBudget == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for(int i=0; i<intCosts.length; i++) {
            int newBudget = intBudget - intCosts[i];
            if ( newBudget >= 0) {
                int cur_result = map.containsKey(newBudget)? map.get(newBudget): search(costs, intCosts, newBudget, map);
                if (min > cur_result) {
                    min = cur_result;
                }
            }
        }
        if (min != Integer.MAX_VALUE) {
            min++;
        }

        map.put(intBudget, min);
        return min;
    }

    public static void main(String[] args) {
        double[] costs = {6.8, 5.7, 3.4, 10.2,4.5,3.9};
        double budget = 29.9;
        MinTrip2 solution = new MinTrip2();
     //   System.out.println(10.2-6.8);
        System.out.println("Min trip2 = " +solution.findMinTrips(costs, budget));
    }

}
