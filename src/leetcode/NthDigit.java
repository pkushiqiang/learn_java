package leetcode;

public class NthDigit {

    public static int findNthDigit(int n) {
        if (n<10)
            return n;

        long s = 1;
        long sumLen = 9;
        long lastSum = 0;
        long width = 1;
        while (sumLen < n) {
            width++;
            lastSum = sumLen;
            s*=10;
            sumLen += (s*9)*width;
        }

        if (sumLen == n)
            return 9;

        long q =  ( n-lastSum) / width;
        long m =  ( n-lastSum) % width;
        long num = s + q - 1 + ((m==0)?0:1);
        System.out.print(String.format("%d num is %d, ", n, num));
        if (m==0)
            return (int)num%10;
        return Long.toString(num).charAt((int)m)-'0';
    }

    private static void print(int n){
        System.out.println(String.format("%d -> %d", n, findNthDigit(n)));
    }

    public static void main(String[] args) {
   /*     print(9);
        print(100);
        print(1000);
        print(10000);
        print(100000);
        print(1000000);
        print(10000000);
        print(100000000);*/
        print(1000000000);
    }
}
