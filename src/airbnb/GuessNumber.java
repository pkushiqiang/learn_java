package airbnb;

import java.util.*;
import java.util.stream.Collectors;

public class GuessNumber {

    private String value;
    private int times;

    public GuessNumber(String v) {
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
        int t = 0;
        for(char i='1'; i<'6'; i++) {
            for (int j=0;j<4;j++) tt[j] = i;
            int m = tcpGuess(String.valueOf(tt));
            map.put(i, m);
            t+=m;
        }
        map.put('6', 4-t);
        List<Character> list = new ArrayList<>();

        list.addAll(map.keySet().stream().filter(k -> map.get(k) > 0).collect(Collectors.toList()));
        Collections.sort(list, Collections.reverseOrder());

        boolean[] flag = new boolean[4];
        char c1 = list.get(0);
        int cm = map.get(c1);
        char[] ca = {c1, c1, c1, c1};
        for(int i=1; i<list.size(); i++) {
            char cc = list.get(i);
            int round = map.get(cc);
            for (int j=0; j< round; j++) {
                for(int k=0; k<4; k++) {
                    if (!flag[k]) {
                        char tc = ca[k];
                        ca[k] = cc;
                        int tm = this.tcpGuess(String.valueOf(ca));
                        if (tm > cm) {
                            cm = tm;
                            flag[k] = true;
                            break;
                        } else if (tm < cm) {
                            ca[k] = tc;
                            flag[k] = true;
                        } else {
                            ca[k] = tc;
                        }
                    }
                }
            }
        }
        return String.valueOf(ca);
    }

    public static void main(String[] args) {
        GuessNumber solution = new GuessNumber("6521");
        System.out.println(solution.guess());
        System.out.println(solution.times);
    }

}
