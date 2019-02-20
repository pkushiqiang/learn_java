package airbnb;

import java.util.*;
import java.util.stream.Collectors;

public class GuessNumber2 {

    private String value;
    private int times;

    public GuessNumber2(String v) {
        this.value = v;
    }

    public int tcpGuess(String str) {
        int c = 0;
        for (int i=0; i<4;i++) {
            if (str.charAt(i) == this.value.charAt(i)) {
                c++;
            }
        }
        this.times++;
        return c;
    }

    public String guess(){

        Map<Character, Integer> map = new HashMap<>();
        char[] tt =new char[4];
        char c0 = 0;
        int t = 0;
        for(char i='1'; i<'6'; i++) {
            for (int j=0;j<4;j++) tt[j] = i;
            int m = tcpGuess(String.valueOf(tt));
            map.put(i, m);
            t+=m;
            if (m==0) {
                c0 = i;
            }
        }
        map.put('6', 4-t);

        char[] res = new char[4];
        Map<Character, Integer> cmap = map;
        for(int i=1; i<4; i++) {
            char[] ca = {c0, c0, c0, c0};
            List<Character> list = new ArrayList<>();
            for(char c: cmap.keySet()) {
                if (cmap.get(c) > 0) {
                    list.add(c);
                }
            }

            Map<Character, Integer> map2 = new HashMap<>();
            t = 0;
            for(int k=0; k<list.size()-1; k++) {
                char c = list.get(k);
                for(int j=i; j< ca.length; j++) {
                    ca[j] = c;
                }
                int tm = this.tcpGuess(String.valueOf(ca));
                if (tm < cmap.get(c)) {
                    res[i-1] = c;
                }
                t+=tm;
                map2.put(c,tm);
            }
            char c = list.get(list.size()-1);
            int tm = 4-i - t;
            if (tm < cmap.get(c)) {
                res[i-1] = c;
            }
            map2.put(c, tm);
            cmap = map2;
        }

        for(int i=0; i<3;i++) {
            char c = res[i];
            int v = map.get(c)-1;
            if (v==0) {
                map.remove(c);
            } else {
                map.put(c, v);
            }
        }
        char c = map.keySet().iterator().next();
        res[3] = c;
        return String.valueOf(res);
    }

    public static void main(String[] args) {
        GuessNumber2 solution = new GuessNumber2("6521");
        System.out.println(solution.guess());
        System.out.println(solution.times);
    }

}
