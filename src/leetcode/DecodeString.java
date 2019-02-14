package leetcode;

public class DecodeString {

    int p = 0;
    public String decodeString(String s) {
        return parse(s);
    }

    private String parse(String s) {
        int n = 0;
        StringBuilder sb = new StringBuilder();
        while (p<s.length()) {
            char c = s.charAt(p);
            p++;
            if ( c >= '0' && c <= '9') {
                n = n*10 + (int)(c - '0');
            } else if ( c>='a' && c<='z') {
                sb.append(c);
            } else if ( c == '[') {

                String str = parse(s);
                System.out.println("str ==" + str );
                for (int i = 0; i < n; i++) {
                    sb.append(str);
                }
                n = 0;
            } else if (c == ']') {

                return sb.toString();
            }
        }
        return sb.toString();
    }

    private static void printresult(DecodeString solution, String input) {
        System.out.println(String.format("%s -> %s " , input, solution.decodeString(input)));
    }

    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
    //    printresult(solution,"abc");
        printresult(solution,"2[ac]");
    }

}
