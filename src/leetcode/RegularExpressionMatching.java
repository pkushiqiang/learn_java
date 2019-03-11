package leetcode;

public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {

        boolean[][] m = new boolean[p.length()+1][s.length()+1];
        m[0][0] = true;

        for(int i=0 ; i<p.length(); i++) {
            char c = p.charAt(i);
            if (c == '*') {
                char pc = p.charAt(i-1);
                for (int j = 0; j <= s.length(); j++) {
                    m[i+1][j] = m[i-1][j];
                    if (m[i][j]) {
                        m[i + 1][j] = true;
                        for (; j < s.length() && (s.charAt(j) == pc || pc == '.' ); j++) {
                            m[i + 1][j+1] = true;
                        }
                        break;
                    }
                }

            } else {
                for(int j=0; j<s.length(); j++) {
                    if (m[i][j]) {
                        if (c == '.' || c == s.charAt(j)) {
                            m[i + 1][j + 1] = true;
                        }
                    }
                }
            } // else
        } // for i

        return m[p.length()][s.length()];
    }

    public static void main(String[] args) {
        RegularExpressionMatching solution = new RegularExpressionMatching();
        System.out.println(solution.isMatch("abcde",".*c*c*.*e") + ", true");

        /*
        System.out.println(solution.isMatch("aabcbcbcaccbcaabc",".*a*aa*.*b*.c*.*a*") + ", true");

        System.out.println(solution.isMatch("aa","a*") + ", true");

        System.out.println(solution.isMatch("",".") + ", false");
        System.out.println(solution.isMatch("",".*") + ", true");

        System.out.println(solution.isMatch("aa","a") + ", false");
        System.out.println(solution.isMatch("aa","a*") + ", true");
        System.out.println(solution.isMatch("aab","c*a*b") + ", true");
        System.out.println(solution.isMatch("mississippi","mis*is*p*.") + ", false");
      */

    }
}
