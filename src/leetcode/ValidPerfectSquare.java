package leetcode;

public class ValidPerfectSquare {


    public static boolean isPerfectSquare(int num) {
        long s = 0, e = num;
        while(s <= e) {
            long m = (s+e)/2;
            long sq = m*m;
            if ( sq < 0 || sq > num) {
                e = m-1;
            } else if ( sq < num) {
                s = m+1;
            } else {
                return true;
            }
        }
        return false;
    }

    public static void main(final String[] args) {
        /*
         System.out.println(isPerfectSquare(8));
        System.out.println(isPerfectSquare(5));
        System.out.println(isPerfectSquare(4));
        System.out.println(isPerfectSquare(9));
        */
        System.out.println(isPerfectSquare(2147395600));
        int a = 2147395600/2;
        int b = a * a;
        long c = (long) a * (long)a;
        System.out.println(b);
        System.out.println(c);
    }
}
