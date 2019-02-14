package airbnb;

import java.math.BigDecimal;

public class Decimal {

    public static void main(String[] args) {
        BigDecimal b1 = BigDecimal.valueOf(5.2);
        BigDecimal b2 = new BigDecimal(4.8);

        BigDecimal b3 = b1.add(b2);
        System.out.println(b3);
        System.out.println(b3.doubleValue());


        BigDecimal b5 = new BigDecimal(4.8);

    }
}
