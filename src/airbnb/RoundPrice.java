package airbnb;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class RoundPrice {

    class Pair {
        int round;
        double value;
        int index;

        public Pair(double value, int round, int index) {
            this.round = round;
            this.value = value;
            this.index = index;
        }
    }

    public int[] roundPrice(double[] prices) {
        Pair[] res = new Pair[prices.length];
        double sum = DoubleStream.of(prices).sum();
        long roundSum = Math.round(sum);
        long subsum = 0;

        for(int i=0; i<prices.length; i++) {
            res[i]  = new Pair(prices[i],  (int)Math.floor(prices[i]), i);
            subsum += res[i].round;
        }

        Arrays.sort(res, (a, b) -> compareRound(a.value , b.value));
        for(int i=0; i < roundSum - subsum; i++) {
            res[i].round++;
        }

        int[] round = new int[prices.length];
        for(int i=0; i<prices.length; i++) {
            round[res[i].index] = res[i].round;
        }
        return round;
    }

    public int compareRound(double a, double b) {
        double factorA = a - Math.floor(a);
        double factorB = b - Math.floor(b);

        if (factorA > factorB)
            return -1;
        if (factorA < factorB)
            return 1;
        return 0;
    }

    public static void main(String[] args) {

        double input1[] = { 2.4f, 3.3f, 3.9f, 20.1f, 30.3f };
        double input2[] = { 2.4f, 3.4f, 3.4f, 20.4f, 30.4f };
        double input3[] = { 2.5f, 3.5f, 3.5f, 20.5f, 30.5f };

        RoundPrice solution = new RoundPrice();

        int output[] = solution.roundPrice(input2);

        for (int val : output) {
            System.out.println(val);
        }
    }
}
