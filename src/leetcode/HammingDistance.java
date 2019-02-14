package leetcode;



public class HammingDistance {

    public int hammingDistance(int x, int y) {
        int c = x^y;
        System.out.println(String.format("x= %s", Integer.toBinaryString(x)));
        System.out.println(String.format("y= %s", Integer.toBinaryString(y)));
        System.out.println(String.format("c= %s", Integer.toBinaryString(c)));
        int r = 0;
        while(c>0) {
            if ( (c&1) > 0 ) {
                r++;
            }
            c>>=1;
        }
        return r;
    }

    public static void main(String[] args) {
        HammingDistance solution = new HammingDistance();
        System.out.println(solution.hammingDistance(4,1));
    }
}
