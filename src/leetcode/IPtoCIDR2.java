package leetcode;

import java.util.ArrayList;
import java.util.List;

public class IPtoCIDR2 {

    public List<String> ipToCIDR(String ip, int n) {
        List<String> res = new ArrayList<>();
        long o = ip2long(ip);
        long v = o;
        long t = v + n;
        while(v<t) {
            System.out.println("=============================================");
            int x = 0;
            long mask = 0;
            while ( (v & mask) == 0 && (v + (1<<x)-1)<t) {
                mask = (1<<x);
                x++;
                System.out.println("-----------------------------------------");
                System.out.println("v=" + Long.toString(v,2) );
                System.out.println("m=" + Long.toString(mask,2) );
                System.out.println("x=" + x );
            }
            System.out.println("x2=" + x );
            res.add(long2ip(v,x-1));
            v = v + (long)(1<< (x-1));
            System.out.println("v2=" + Long.toString(v,2) );
        }
        return res;
    }

    private long ip2long(String ip) {
        String[] strs = ip.split("\\.");
        long v = 0;
        for(String str: strs) {
            int j = 0;
            for(int i=0; i<str.length(); i++) {
                j = j*10 + (str.charAt(i) - '0');
            }
            v = v * 256 + j;
        }
        return v;
    }

    private String long2ip(long v, int x) {
        long[] strs = new long[4];
        for(int i=0;i<4;i++) {
            strs[i] =  v%256;
            v /= 256;
        }
        return strs[3] + "."+ strs[2] + "." + strs[1] + "." + strs[0] + "/" + (32-x);
    }


    public static void main(String[] args) {
        String ip = "10.166.253.147";
        int n = 12;
        IPtoCIDR IPtoCIDR = new IPtoCIDR();

        List<String> result = IPtoCIDR.ipToCIDR(ip, n);
        System.out.println("=========================================");
        for (String str : result) {
            System.out.println(str);
        }

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++");
        IPtoCIDR2 IPtoCIDR2 = new IPtoCIDR2();

        List<String> result2 = IPtoCIDR2.ipToCIDR(ip, n);
        System.out.println("=========================================");
        for (String str : result2) {
            System.out.println(str);
        }
    }
}
