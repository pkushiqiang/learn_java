package leetcode;

public class WildcardMatching2 {

    public boolean isMatch(String s, String p) {

        boolean[][] m = new boolean[p.length()+1][s.length()+1];
        m[0][0] = true;

        for(int i=0 ; i<p.length(); i++) {
            char c = p.charAt(i);

            if (c == '*') {
                for(int j=0; j<=s.length(); j++) {
                    if (m[i][j]) {
                        for(; j<=s.length(); j++) {
                            m[i+1][j] = true;
                        }
                    }
                }
            } else {
                for(int j=0; j<s.length(); j++) {
                    if (m[i][j]) {
                        if (c == '?' || c == s.charAt(j)) {
                            m[i + 1][j + 1] = true;
                        }
                    }
                }
            } // else
        } // for i

        return m[p.length()][s.length()];
    }

    public static void main(String[] args) {
        WildcardMatching2 solution = new WildcardMatching2();

        System.out.println(solution.isMatch("","*") + ", true");
        System.out.println(solution.isMatch("a","*") + ", true");
        System.out.println(solution.isMatch("aab","*b") + ", true");
        System.out.println(solution.isMatch("aa","a*") + ", true");
        System.out.println(solution.isMatch("a","a*") + ", true");

        System.out.println(solution.isMatch("adceb","*a*b") + ", true");

    }
}
