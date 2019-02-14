package airbnb;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinTrip {

    private static final int SCALE =10;

    static public class Result {
        int num;
        List<Double> list = new ArrayList<>();

        public Result(int num){
            this.num = num;
        }

        public Result(int num, List<Double> list){
            this.num = num;
            this.list = list;
        }
    }

    public Result findMinTrips(double[] costs, double  budget) {
        int intBudget = (int) (budget * SCALE);
        int[] intCosts = new int[costs.length];
        Map<Integer, Result> map = new HashMap<>();

        for(int i=0; i<costs.length; i++) {
            intCosts[i] = (int) (costs[i] * SCALE);
        }

        Result result =search(costs, intCosts, intBudget, map);
        return result;
    }

    private Result search(double[] costs, int[] intCosts, int intBudget, Map<Integer, Result> map) {
        if (intBudget == 0) {
            return new Result(0, new ArrayList<>());
        }

        int min = Integer.MAX_VALUE;
        List<Double> tmpList = new ArrayList<>();
        int trip = -1;

        for(int i=0; i<intCosts.length; i++) {
            int newBudget = intBudget - intCosts[i];
            if ( newBudget >= 0) {
                Result cur_result = map.containsKey(newBudget)? map.get(newBudget): search(costs, intCosts, newBudget, map);
                if (min > cur_result.num) {
                    min = cur_result.num;
                    tmpList = cur_result.list;
                    trip = i;
                }
            }
        }
        if (min != Integer.MAX_VALUE) {
            min++;
        }
        Result result = new Result(min, new ArrayList<>(tmpList));
        if (trip != -1) {
            result.list.add(costs[trip]);
        }
        map.put(intBudget, result);
        return result;
    }

    private static void   printResult(Result result){
        System.out.println("Min trip = " + result.num);
        System.out.print ("list of trip: " );
        for(double d: result.list) {
            System.out.print( d + ", ");
        }
        System.out.println();
    }


    public static void main(String[] args) {
        double[] costs = {6.8, 5.7, 3.4, 10.2, 0.4, 8.5, 8.9, 23.5, 10.7, 4.5, 3.9};
        double budget = 67.6;
        MinTrip solution = new MinTrip();
     //   System.out.println(10.2-6.8);
        printResult(solution.findMinTrips(costs, budget));
    }

}
