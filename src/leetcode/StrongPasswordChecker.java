package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StrongPasswordChecker {

    public int strongPasswordChecker(String s) {
        int hadLowerCase = 1, hasUpCase = 1, hasDigit = 1;
        for(int i=0; i<s.length(); i++) {
            char c= s.charAt(i);
            if ( c >= 'a' && c <= 'z')
                hadLowerCase = 0;
            if ( c >= 'A' && c <= 'Z')
                hasUpCase = 0;
            if ( c >= '0' && c <= '9')
                hasDigit = 0;
        }
        int  con2 =  hadLowerCase + hasUpCase + hasDigit;

        List<Integer> repeated = new ArrayList<>();
        int con3 = 0;
        int i = 0;
        while ( i<s.length() ) {
            char c = s.charAt(i);
            i++;
            int cnt = 1;
            while(i<s.length() && c == s.charAt(i)) {
                i++;
                cnt++;
            }
            if (cnt>2) {
                repeated.add(cnt);
                con3 = con3 + cnt/3;
            }
        }

        int len = s.length();
        if (len >=6 && len <=20) {
            return con3 > con2   ? con3 : con2;
        }

        if (len < 6) {
            return 6 - len > con2 ? 6 - len : con2;
        }
        Collections.sort(repeated);
        // len > 20
        int diff = len -20;
        int r = 0;

        while(diff>0 ) {
            for( i=0; i<repeated.size() ; i++) {
                if (diff == 0)
                    break;
                if ( repeated.get(i)>=3 && repeated.get(i) % 3 == r ) {
                    diff--;
                    repeated.set(i, repeated.get(i)-1);
                }
                r = (r+1)%3;
            }
        }
        return len - 20 + con2;

    }

    public static void main(String[] args) {
        StrongPasswordChecker solution = new StrongPasswordChecker();
        System.out.println(solution.strongPasswordChecker("...."));
        System.out.println(solution.strongPasswordChecker("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"));

    }
}
