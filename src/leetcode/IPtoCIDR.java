package leetcode;

import java.util.ArrayList;
import java.util.List;

public class IPtoCIDR {

    StringBuilder sb = new StringBuilder();

    public List<String> ipToCIDR(String ip, int n) {
        List<String> result = new ArrayList<>();
        if ( n <= 0 || ip == null || ip.isEmpty()) {
            return result;
        }
        long x = ipToLong(ip);
        System.out.println("x=" + x );
        System.out.println("x=" + Long.toString(x,2) );
        long c = x ;
        System.out.println("target = " + toCIDR(x+n-1, 32) );
        while(c < x + n) {
            System.out.println("-----------------------------");
            int z  = countZero(c);
            while ( (c + (1<<z)) > (x + n) ) {
                System.out.println("z=" + z + " ,c + (1<<z) = " + toCIDR(c + (1<<z), 32));
                z--;
            }
            String str = toCIDR(c, z);
            System.out.println("str=" + str);
            System.out.println("z =" + z + ", 1<<z=" + (1<<z));
            result.add(str);

            c += (1<<z);
            System.out.println("ip =" + toCIDR(c, 32) );
            System.out.println("c=" + Long.toString(c,2) );
        }
        return result;
    }

    private int countZero(long x) {
        int c = 0;
        while ( (x & 1) == 0) {
            x >>= 1;
            c++;
        }
        return c;
    }

    private String toCIDR(long x, int z) {
        int[] ips = new int[4];
        for (int i=0; i<4; i++) {
            ips[i] = (int) x & 255;
            x >>= 8;
        }
        sb.append(ips[3]).append(".")
                .append(ips[2]).append(".")
                .append(ips[1]).append(".")
                .append(ips[0])
                .append("/").append(32-z);
        String res = sb.toString();
        sb.setLength(0);
        return res;
    }

    private long ipToLong(String ip) {
        String[] strs = ip.split("\\.");
        long l = 0;
        for (int i=0; i<strs.length; i++) {
            l <<= 8;
            l += Long.valueOf(strs[i]);
        }
        return l;
    }

    public static void main(String[] args) {
        IPtoCIDR IPtoCIDR = new IPtoCIDR();

        List<String> result = IPtoCIDR.ipToCIDR("255.0.0.7", 10);
        System.out.println("=========================================");
        for (String str : result) {
            System.out.println(str);
        }
    }
}
