package leetcode;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        s = " " + s;
        p = " " + p;
        boolean[][] m = new boolean[s.length()][p.length()];

        m[0][0] = true;
        if(p.charAt(1) == '*') {
            for(int i=1; i<s.length(); i++)
                m[i][1] = true;
        }

        for(int j=1; j<p.length(); j++) {
            char c = p.charAt(j);

            for(int i=1 ; i<s.length(); i++) {
                if(m[i-1][j-1]) {
                    if (c == '?' || c == s.charAt(j)) {
                        m[i][j] = true;
                    } else if (c == '*') {
                        m[i][j] = true;
                        for(int k=i-1; k<s.length(); k++)
                            m[k][j] = true;
                    }
                }
            } // for 1
        } // for 2
        return m[s.length()-1][p.length()-1];
    }

    public static void main(String[] args) {
        WildcardMatching solution = new WildcardMatching();
        System.out.println(solution.isMatch(""
                ,"*"));
    }
}
